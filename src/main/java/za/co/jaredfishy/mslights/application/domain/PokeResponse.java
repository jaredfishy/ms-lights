package za.co.jaredfishy.mslights.application.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class PokeResponse {

    private String message;
    private LocalDateTime timestamp;

    public PokeResponse(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    @JsonProperty("timestamp")
    public String getTimestampAsString() {
        return timestamp.toString();
    }
}
