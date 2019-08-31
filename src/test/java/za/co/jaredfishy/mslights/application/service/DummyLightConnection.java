package za.co.jaredfishy.mslights.application.service;

public class DummyLightConnection extends LightConnection {

    public DummyLightConnection(String ipAddress) {
        super(ipAddress);
    }

    @Override
    protected void connect(String ipAddress) {

    }
}
