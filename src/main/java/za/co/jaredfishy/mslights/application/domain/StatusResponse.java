package za.co.jaredfishy.mslights.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class StatusResponse {

    private FormattedDateTime timestamp;

    public StatusResponse(FormattedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @JsonIgnore
    public FormattedDateTime getTimestamp() {
        return timestamp;
    }
    @JsonProperty("timestamp")
    public String getTimestampAsString() {
        return timestamp.toString();
    }
}
