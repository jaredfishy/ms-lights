package za.co.jaredfishy.mslights.application.service;

public class DummyLightConnectionCreator extends LightConnectionCreator {

    private int connectionsCreated = 0;

    @Override
    public LightConnection createConnection(String ip) {
        connectionsCreated++;
        return new DummyLightConnection(ip);
    }

    public int getConnectionsCreated() {
        return connectionsCreated;
    }
}
