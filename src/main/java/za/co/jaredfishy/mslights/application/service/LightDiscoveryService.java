package za.co.jaredfishy.mslights.application.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import za.co.jaredfishy.mslights.application.domain.light.Light;
import za.co.jaredfishy.mslights.application.util.LightParser;
import za.co.jaredfishy.mslights.application.util.OutputFormatter;

import java.net.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Service
public class LightDiscoveryService {

    private static final Logger LOG = LogManager.getLogger(LightDiscoveryService.class);

    private static final int PORT = 1982;
    private static final int TIMEOUT_MILLIS = 2000;
    private static final String NL = "\r\n";

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

    public DatagramSocket getSocket() throws Exception {
        String nifName = "eth1";
        Enumeration<NetworkInterface> nifs = NetworkInterface.getNetworkInterfaces();
        NetworkInterface nif = NetworkInterface.getByName(nifName);
        if (nif == null) {
            LOG.error("No Network Interface found with name \"" + nifName + "\". Using default.");
            return new DatagramSocket();
        }
        LOG.debug("Found Network interface with name: " + nif.getName());
        Enumeration<InetAddress> nifAddresses = nif.getInetAddresses();
        InetSocketAddress inetAddr = new InetSocketAddress(nifAddresses.nextElement(), 0);
        return new DatagramSocket(inetAddr);
    }

    public Map<String, Light> discover() {
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
        LOG.info("Discovered " + lightMap.size() + " device(s)");
        return lightMap;
    }

    public Light getNextLight(DatagramSocket clientSocket) throws Exception {
        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);

        String response = new String(receivePacket.getData());
        LOG.info("Discover Response: " + OutputFormatter.formatOutput(response));
        return LightParser.parse(response);
    }
}
