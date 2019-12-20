package za.co.jaredfishy.mslights.application.util;

import za.co.jaredfishy.mslights.application.domain.light.Light;
import za.co.jaredfishy.mslights.application.domain.yeelight.YeelightResponse;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class DummyDataUtil {

    public static LocalDateTime getTimestamp(){
        return LocalDateTime.of(2020,12,21,13,37,00);
    }

    public static List<Light> getDiscoveryData(LocalDateTime timestamp) {
        return Arrays.asList(new Light(DummyDataUtil.getYeelightResponse(timestamp)));
    }

    public static YeelightResponse getYeelightResponse(LocalDateTime timestamp) {
        return new YeelightResponse(
                "0x0000000007f16b5c",
                "yeelight://192.168.0.100:55443",
                "color",
                "26",
                "set_power set_rgb jaredfishy",
                true,
                100,
                "2",
                3500,
                16711680,
                359,
                100,
                "My First Yeelight!",
                timestamp
        );
    }
}
