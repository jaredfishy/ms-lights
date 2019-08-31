package za.co.jaredfishy.mslights.application.service;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class LightConnectionHandler {

    private LightConnectionCreator lightConnectionCreator;
    private Map<String, LightConnection> connections;

    public LightConnectionHandler(LightConnectionCreator lightConnectionCreator) {
        this.lightConnectionCreator = lightConnectionCreator;
        this.connections = new HashMap<>();
    }

    public LightConnection getConnection(String ip) {
        LightConnection connection = connections.get(ip);
        if (connection == null) {
            connection = lightConnectionCreator.createConnection(ip);
            connections.put(ip, connection);
        }
        return connection;
    }
}
