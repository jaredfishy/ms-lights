package za.co.jaredfishy.mslights.application.domain.light;

import java.time.LocalDateTime;

public class LightVariable {

    private final LocalDateTime timestamp;

    public LightVariable(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
