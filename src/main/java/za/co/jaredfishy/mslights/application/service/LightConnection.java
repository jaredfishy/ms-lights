package za.co.jaredfishy.mslights.application.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class LightConnection {

    private final Logger LOG = LogManager.getLogger(LightConnection.class);

    private static final int PORT = 55443;
    private static final int SOCKET_TIMEOUT = 1500;

    private final String ipAddress;
    private Socket socket;
    private BufferedOutputStream output;
    private BufferedReader inputReader;
    private boolean healthy;


    public LightConnection(String ipAddress) {
        this.ipAddress = ipAddress;
        this.healthy = false;
        connect(ipAddress);
    }

    protected void connect(String ipAddress) {
        InetAddress inetAddress;
        try {
            if (ipAddress != null && !ipAddress.isEmpty())
                inetAddress = InetAddress.getByName(ipAddress);
            else
                inetAddress = InetAddress.getLocalHost();

            socket = new Socket(inetAddress, PORT);
            socket.setSoTimeout(SOCKET_TIMEOUT);

            this.healthy = true;
        } catch (Exception err) {
            err.printStackTrace();
            throw new RuntimeException("Unable to open connection.");
        }
    }


    private BufferedOutputStream getOutputStream() throws Exception {
        if (output == null) {
            output = new BufferedOutputStream(socket.getOutputStream());
            LOG.debug("Got output stream");
        }
        return output;
    }

    private BufferedReader getInputReader() throws Exception {
        if (inputReader == null) {
            inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            LOG.debug("Got input stream");
        }
        return inputReader;
    }

    public boolean isHealthy() {
        return this.healthy && !socket.isClosed();
    }

    public String send(String message) throws SocketTimeoutException {
        try {
            BufferedOutputStream output = getOutputStream();

            LOG.info("Sending message to " + ipAddress + ": " + message);
            output.write(message.getBytes());
            output.write("\r\n".getBytes());
            output.flush();

            BufferedReader input = getInputReader();
            String response = input.readLine();
            LOG.info("Received message from " + ipAddress + ": " + response);
            return response;
        } catch (SocketTimeoutException err) {
            healthy = false;
            throw err;
        } catch (Exception err) {
            String errorMessage = "Unable to send command to " + ipAddress;
            LOG.error(errorMessage, err);
            healthy = false;
            throw new RuntimeException(errorMessage);
        }
    }

    public void cleanup() throws Exception {
        if (output != null) {
            try {
                output.close();
            } catch (Exception err) {
            }
            output = null;
        }
        if (inputReader != null) {
            try {
                inputReader.close();
            } catch (Exception err) {
            }
            inputReader = null;
        }
        if (socket != null) {
            try {
                socket.close();
            } catch (Exception err) {
            }
            socket = null;
        }
    }
}
