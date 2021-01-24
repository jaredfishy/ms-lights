package za.co.jaredfishy.mslights.application.domain.light;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import za.co.jaredfishy.mslights.application.domain.FormattedDateTime;

public class LightVariable {

    private final FormattedDateTime timestamp;

    public LightVariable(FormattedDateTime timestamp) {
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
