package za.co.jaredfishy.mslights.application.util;

import za.co.jaredfishy.mslights.application.domain.light.LightLocation;
import za.co.jaredfishy.mslights.application.domain.yeelight.YeelightResponse;

import java.time.LocalDateTime;

public class LightLocationBuilder {

    public static LightLocation build(YeelightResponse yeelightResponse) {
        String[] locationBits = yeelightResponse.getLocation().split(":");
        String ip = locationBits[1].substring(2);
        int port = Integer.parseInt(locationBits[2]);

        return new LightLocation(
                yeelightResponse.getTimestamp(),
                ip,
                port
        );
    }
}
