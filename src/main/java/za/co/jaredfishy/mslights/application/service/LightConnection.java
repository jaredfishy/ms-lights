package za.co.jaredfishy.mslights.application.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class LightConnection {

    private final Logger LOG = LogManager.getLogger(LightConnection.class);

    private static final int PORT = 55443;
    private final String ipAddress;
    private Socket server;
    private BufferedOutputStream output;
    private BufferedReader inputReader;



    public LightConnection(String ipAddress) {
        this.ipAddress = ipAddress;
        connect(ipAddress);
    }

    protected void connect(String ipAddress) {
        InetAddress inetAddress;
        try {
            if (ipAddress != null && !ipAddress.isEmpty())
                inetAddress = InetAddress.getByName(ipAddress);
            else
                inetAddress = InetAddress.getLocalHost();

            this.server = new Socket(inetAddress, PORT);
        } catch (Exception err) {
            err.printStackTrace();
            throw new RuntimeException("Unable to open connection.");
        }
    }


    private BufferedOutputStream getOutputStream() throws Exception {
        if (output == null) {
            output = new BufferedOutputStream(this.server.getOutputStream());
            LOG.debug("Got output stream");
        }
        return output;
    }

    private BufferedReader getInputReader() throws Exception {
        if (inputReader == null) {
            inputReader = new BufferedReader(new InputStreamReader(this.server.getInputStream()));
            LOG.debug("Got input stream");
        }
        return inputReader;
    }

    public boolean isClosed(){
        return server.isClosed();
    }

    public String send(String message){

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

        } catch (Exception err) {
            String errorMessage = "Unable to send command to " + ipAddress;
            LOG.error(errorMessage, err);
            err.printStackTrace();
            throw new RuntimeException(errorMessage);
        }finally {
            try {
                this.close();
            } catch (Exception e) {
            }
        }
    }

    public void close() throws Exception {
        if (output != null) output.close();
        if (inputReader != null) inputReader.close();
        if (server != null) server.close();
    }
}
