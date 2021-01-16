package za.co.jaredfishy.mslights.application.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import za.co.jaredfishy.mslights.application.domain.light.Light;
import za.co.jaredfishy.mslights.application.domain.yeelight.YeelightResponse;
import za.co.jaredfishy.mslights.application.util.OutputFormatter;
import za.co.jaredfishy.mslights.application.util.YeelightResponseParser;

import java.net.*;
import java.util.*;

@Service
public class LightDiscoveryService {

    private static final Logger LOG = LogManager.getLogger(LightDiscoveryService.class);

    private static final int PORT = 1982;
    private static final int TIMEOUT_MILLIS = 2000;
    private static final String NL = "\r\n";

    @Value("${network.interface.name}")
    private String networkInterfaceName;

    private String getDiscoveryString() {

        StringBuilder builder = new StringBuilder();
        builder.append("M-SEARCH * HTTP/1.1");
        builder.append(NL);
        builder.append("HOST:239.255.255.250:1982");
        builder.append(NL);
        builder.append("MAN:\"ssdp:discover\"");
        builder.append(NL);
        builder.append("ST:wifi_bulb");
        builder.append(NL);
        return builder.toString();
    }

    private DatagramSocket getSocket() throws Exception {

        if (networkInterfaceName.length() == 0) {
            LOG.info("Preferred Network Interface not specified. Using default.");
            return new DatagramSocket();
        }

        if (networkInterfaceName.equals("*")) {
            return getAnySocket();
        } else {
            NetworkInterface nif = NetworkInterface.getByName(networkInterfaceName);
            try {
                return trySocket(nif);
            } catch (Exception err) {

            }
            LOG.info("Preferred Network Interface not found. Using default.");
            return new DatagramSocket();
        }
    }

    private DatagramSocket getAnySocket() throws Exception {
        Enumeration<NetworkInterface> nifs = NetworkInterface.getNetworkInterfaces();
        while (nifs.hasMoreElements()) {
            NetworkInterface nif = nifs.nextElement();
            if (nif.getName().startsWith("eth")) {
                try {
                    return trySocket(nif);
                } catch (Exception err) {
                    LOG.error("Network Interface \"" + nif.getName() + "\" did not work.");
                }
            }
        }
        LOG.info("No Network Interface found. Using default.");
        return new DatagramSocket();
    }

    private DatagramSocket trySocket(NetworkInterface nif) throws Exception {
        LOG.debug("Found Network interface with name: " + nif.getName());
        Enumeration<InetAddress> nifAddresses = nif.getInetAddresses();
        InetSocketAddress inetAddr = new InetSocketAddress(nifAddresses.nextElement(), PORT);
        LOG.info("Found interface: " + nif.getName());
        return new DatagramSocket(inetAddr);
    }

    public List<Light> discover() {
        Map<String, Light> lightMap = new HashMap<>();
        DatagramSocket clientSocket = null;
        try {

            String msearch = getDiscoveryString();
            byte[] sendData = msearch.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("239.255.255.250"), PORT);

            clientSocket = getSocket();
            clientSocket.setSoTimeout(TIMEOUT_MILLIS);
            clientSocket.send(sendPacket);

            while (true) {
                Light light = getNextLight(clientSocket);
                String id = light.getId();
                if (!lightMap.containsKey(id))
                    lightMap.put(id, light);
            }


        } catch (SocketTimeoutException socketTimeoutException) {
            // we expect it to timeout here
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (clientSocket != null)
                clientSocket.close();
        }
        List<Light> lightList = new ArrayList(lightMap.values());
        LOG.info("Discovered " + lightList.size() + " device(s)");
        return lightList;
    }

    public Light getNextLight(DatagramSocket clientSocket) throws Exception {
        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);

        String response = new String(receivePacket.getData());
        LOG.debug("Discover Response: " + OutputFormatter.formatOutput(response));
        YeelightResponse yeelightResponse = YeelightResponseParser.parse(response);
        return new Light(yeelightResponse);
    }
}
