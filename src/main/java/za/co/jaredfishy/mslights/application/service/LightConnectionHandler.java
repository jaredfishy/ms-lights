package za.co.jaredfishy.mslights.application.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class LightConnectionHandler {

    private static final Logger LOG = LogManager.getLogger(LightConnectionHandler.class);

    private static LightConnectionHandler lightConnectionHandler = null;


    private static void createNewInstance() {
        lightConnectionHandler = new LightConnectionHandler(new LightConnectionCreator());
    }

    public static LightConnectionHandler getInstance() {
        if (lightConnectionHandler == null) createNewInstance();
        return lightConnectionHandler;
    }

    public static void reinitialise() {
        if (lightConnectionHandler != null)
            lightConnectionHandler.closeConnections();
        createNewInstance();
    }

    private LightConnectionCreator lightConnectionCreator;
    private Map<String, LightConnection> connections;

    LightConnectionHandler(LightConnectionCreator lightConnectionCreator) {
        this.lightConnectionCreator = lightConnectionCreator;
        this.connections = new HashMap<>();
    }

    LightConnection getConnection(String ip) {
        LightConnection connection = connections.get(ip);
        if (!isValidConnection(ip, connection)) {
            connection = lightConnectionCreator.createConnection(ip);
            connections.put(ip, connection);
        }
        return connection;
    }

    public boolean isValidConnection(
            String ip,
            LightConnection connection
    ) {
        if (connection == null) return false;
        if (connection.isHealthy()) {
            try {
                connection.cleanup();
            }catch(Exception err){

            }
            connections.remove(ip);
            return false;
        }
        return true;
    }

    public int getConnectionCount() {
        return connections.size();
    }

    private synchronized void closeConnections() {
        for (LightConnection connection : connections.values()) {
            try {
                connection.cleanup();
            } catch (Exception err) {
                err.printStackTrace();
            }
        }
    }
}
