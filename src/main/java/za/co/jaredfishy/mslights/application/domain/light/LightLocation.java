package za.co.jaredfishy.mslights.application.domain.light;

import za.co.jaredfishy.mslights.application.domain.FormattedDateTime;

public class LightLocation extends LightVariable {

    private final String ip;
    private final int port;


    public LightLocation(FormattedDateTime timestamp, String ip, int port) {
        super(timestamp);
        this.ip = ip;
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }
}
