package za.co.jaredfishy.mslights.application.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class LightConnectionV2 {

    private final Logger LOG = LogManager.getLogger(LightConnectionV2.class);

    private static final int SOCKET_TIMEOUT = 1500;

    private final String ip;
    private final int port;

    public LightConnectionV2(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    private Socket createSocket(String ipAddress) {
        InetAddress inetAddress;
        try {
            if (ipAddress != null && !ipAddress.isEmpty())
                inetAddress = InetAddress.getByName(ipAddress);
            else
                inetAddress = InetAddress.getLocalHost();

            Socket socket = new Socket(inetAddress, port);
            socket.setSoTimeout(SOCKET_TIMEOUT);
            return socket;
        } catch (Exception err) {
            err.printStackTrace();
            throw new RuntimeException("Unable to open connection.");
        }
    }
    public String send(String message) throws SocketTimeoutException {
        Socket socket = null;
        BufferedOutputStream output = null;
        BufferedReader reader = null;
        try {
            socket = createSocket(ip);
            output = new BufferedOutputStream(socket.getOutputStream());

            LOG.info("Sending message to " + ip + ": " + message);
            output.write(message.getBytes());
            output.write("\r\n".getBytes());
            output.flush();

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = reader.readLine();
            LOG.info("Received message from " + ip + ": " + response);
            return response;
        } catch (SocketTimeoutException err) {
            throw err;
        } catch (Exception err) {
            String errorMessage = "Unable to send command to " + ip;
            LOG.error(errorMessage, err);
            throw new RuntimeException(errorMessage);
        }
        finally {
            if(reader!= null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
            if(output!= null) {
                try {
                    output.close();
                } catch (IOException e) {
                }
            }
            if(socket!= null) {
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
