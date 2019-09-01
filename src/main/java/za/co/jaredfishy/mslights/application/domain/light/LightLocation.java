package za.co.jaredfishy.mslights.application.domain.light;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LightLocation {

    private final String ip;
    private final int port;

    @JsonCreator
    public LightLocation(
            @JsonProperty("ip") String ip,
            @JsonProperty("port") int port
    ) {
        this.ip = ip;
        this.port = port;
    }

    public String getIp() {
        return ip;
    }
}
