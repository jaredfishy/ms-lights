package za.co.jaredfishy.mslights.application.domain.light;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.StringUtils;
import za.co.jaredfishy.mslights.application.domain.yeelight.YeelightResponse;
import za.co.jaredfishy.mslights.application.util.JSONSerializer;
import za.co.jaredfishy.mslights.application.util.LightLocationBuilder;
import za.co.jaredfishy.mslights.application.util.LightStatusBuilder;

import java.util.Arrays;

public class Light extends LightDevice {

    private LightLocation location;
    private LightStatus status;

    public Light(YeelightResponse yeelightResponse) {
        super(
                LightBrand.YEELIGHT,
                yeelightResponse.getId(),
                yeelightResponse.getName(),
                yeelightResponse.getModel(),
                yeelightResponse.getFirmwareVersion(),
                Arrays.asList(StringUtils.split(yeelightResponse.getSupport(), " "))
        );

        this.location = LightLocationBuilder.build(yeelightResponse);
        this.status = LightStatusBuilder.build(yeelightResponse);
    }

    @JsonProperty("location")
    public LightLocation getLocation() {
        return location;
    }

    @JsonProperty("status")
    public LightStatus getStatus() {
        return status;
    }

    public String toString(){
        return JSONSerializer.serialize(this, super.toString());
    }
}
