package za.co.jaredfishy.mslights.application.util;

import za.co.jaredfishy.mslights.application.domain.light.Light;
import za.co.jaredfishy.mslights.application.domain.light.LightLocation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LightParser {

    private static final String KEY_LOCATION = "Location";
    private static final String KEY_ID = "id";
    private static final String KEY_MODEL = "model";
    private static final String KEY_FW_VER = "fw_ver";
    private static final String KEY_SUPPORT = "support";
    private static final String KEY_POWER = "power";
    private static final String KEY_BRIGHT = "bright";
    private static final String KEY_COLOR_MODE = "color_mode";
    private static final String KEY_CT = "ct";
    private static final String KEY_RGB = "rgb";
    private static final String KEY_HUE = "hue";
    private static final String KEY_SAT = "sat";
    private static final String KEY_NAME = "name";


    public static Light parse(String input) {

        List<String> lines = Arrays.asList(input.split("\r\n"));
        Map<String, String> map = new HashMap<>();
        for (String line : lines) {

            int index = line.indexOf(":");
            if (index < 0) continue;

            String key = line.substring(0, index);
            String val = line.substring(index + 1).trim();
            map.put(key, val);
        }

        // Location: yeelight://192.168.0.103:55443
        String locationString = map.get(KEY_LOCATION);
        LightLocation location = parseLocation(locationString);

        String supportString = map.get(KEY_SUPPORT);
        //List<CommandMethod> support = parseCommands(supportString);
        // get_prop set_default set_power toggle set_bright start_cf stop_cf set_scene cron_add cron_get cron_del set_ct_abx set_rgb set_hsv set_adjust adjust_bright adjust_ct adjust_color set_music set_name

        return new Light(
                map.get(KEY_ID),
                map.get(KEY_MODEL),
                map.get(KEY_FW_VER),
                supportString,
                map.get(KEY_POWER).equalsIgnoreCase("on"),
                Integer.parseInt(map.get(KEY_BRIGHT)),
                map.get(KEY_COLOR_MODE),
                Integer.parseInt(map.get(KEY_CT)),
                Long.parseLong(map.get(KEY_RGB)),
                Integer.parseInt(map.get(KEY_HUE)),
                Integer.parseInt(map.get(KEY_SAT)),
                map.get(KEY_NAME),
                location
        );
    }

    public static LightLocation parseLocation(String location) {

        String[] locationBits = location.split(":");
        String ip = locationBits[1].substring(2);
        String port = locationBits[2];

        return new LightLocation(
                ip,
                Integer.parseInt(port)
        );
    }
}
