package za.co.jaredfishy.mslights.application.service;

import za.co.jaredfishy.mslights.application.domain.command.Command;
import za.co.jaredfishy.mslights.application.util.CommandMessageCreator;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class LightConnection {

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
            System.out.println("Got output stream");
        }
        return output;
    }

    private BufferedReader getInputReader() throws Exception {
        if (inputReader == null) {
            inputReader = new BufferedReader(new InputStreamReader(this.server.getInputStream()));
            System.out.println("Got input stream");
        }
        return inputReader;
    }

    public void send(Command command) {
        String message = CommandMessageCreator.getMessage(command);
        send(message);
    }

    public String send(String message) {

        try {
            BufferedOutputStream output = getOutputStream();

            System.out.println("Sending message: " + message);
            output.write(message.getBytes());
            output.write("\r\n".getBytes());
            output.flush();

            BufferedReader input = getInputReader();
            String response = input.readLine();
            System.out.println("Received message: " + response);
            return response;

        } catch (Exception err) {
            err.printStackTrace();
            throw new RuntimeException("Unable to send command");
        }
    }

    public void close() throws Exception {
        if (output != null) output.close();
        if (inputReader != null) inputReader.close();
        if (server != null) server.close();
    }

    public String getIpAddress() {
        return ipAddress;
    }
}
