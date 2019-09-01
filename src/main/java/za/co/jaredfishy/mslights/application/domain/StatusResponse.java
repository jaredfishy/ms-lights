package za.co.jaredfishy.mslights.application.domain;

import java.time.LocalDateTime;

public class StatusResponse {

    private LocalDateTime timestamp;
    private int connectionCount;

    public StatusResponse(LocalDateTime timestamp, int connectionCount) {
        this.timestamp = timestamp;
        this.connectionCount = connectionCount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getConnectionCount() {
        return connectionCount;
    }
}
