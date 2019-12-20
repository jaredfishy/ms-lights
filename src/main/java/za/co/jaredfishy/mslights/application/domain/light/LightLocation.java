package za.co.jaredfishy.mslights.application.domain.light;

import java.time.LocalDateTime;

public class LightLocation extends LightVariable {

    private final String ip;
    private final int port;


    public LightLocation(LocalDateTime timestamp, String ip, int port) {
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
