package za.co.jaredfishy.mslights.application.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class StatusResponse {

    private LocalDateTime timestamp;
    private int connectionCount;

    public StatusResponse(LocalDateTime timestamp, int connectionCount) {
        this.timestamp = timestamp;
        this.connectionCount = connectionCount;
    }

    @JsonProperty("timestamp")
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @JsonProperty("connectionCount")
    public int getConnectionCount() {
        return connectionCount;
    }
}
