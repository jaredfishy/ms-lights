package za.co.jaredfishy.mslights.application.util;

import za.co.jaredfishy.mslights.application.domain.light.LightStatus;
import za.co.jaredfishy.mslights.application.domain.yeelight.YeelightResponse;

public class LightStatusBuilder {

    public static LightStatus build(YeelightResponse yeelightResponse) {
        return new LightStatus(
                yeelightResponse.getTimestamp(),
                yeelightResponse.isPowered(),
                yeelightResponse.getBright(),
                yeelightResponse.getColorMode(),
                yeelightResponse.getCt(),
                yeelightResponse.getRgb(),
                yeelightResponse.getHue(),
                yeelightResponse.getSat()
        );
    }
}
