package za.co.jaredfishy.mslights.application.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class PokeResponse {

    private String message;
    private FormattedDateTime timestamp;

    public PokeResponse(String message) {
        this.message = message;
        this.timestamp = FormattedDateTime.now();
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("timestamp")
    public String getTimestampAsString() {
        return timestamp.toString();
    }
}
