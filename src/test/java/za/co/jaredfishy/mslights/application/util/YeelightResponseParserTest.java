package za.co.jaredfishy.mslights.application.util;

import org.junit.Assert;
import org.junit.Test;
import za.co.jaredfishy.mslights.application.domain.yeelight.YeelightResponse;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;

public class YeelightResponseParserTest {

    @Test
    public void parse() {
        YeelightResponse actual = YeelightResponseParser.parse(getString());
        YeelightResponse expected = DummyDataUtil.getYeelightResponse(actual.getTimestamp());
        Assert.assertThat(expected, sameBeanAs(actual));
    }

    private String getString() {
        StringBuilder bob = new StringBuilder();
        bob.append("HTTP/1.1 200 OK\r\n");
        bob.append("Cache-Control: max-age=3584\r\n");
        bob.append("Date:\r\n");
        bob.append("Ext:\r\n");
        bob.append("Location: yeelight://192.168.0.100:55443\r\n");
        bob.append("Server: POSIX UPnP/1.0 YGLC/1\r\n");
        bob.append("id: 0x0000000007f16b5c\r\n");
        bob.append("model: color\r\n");
        bob.append("fw_ver: 26\r\n");
        bob.append("support: set_power set_rgb jaredfishy\r\n");
        bob.append("power: on\r\n");
        bob.append("bright: 100\r\n");
        bob.append("color_mode: 2\r\n");
        bob.append("ct: 3500\r\n");
        bob.append("rgb: 16711680\r\n");
        bob.append("hue: 359\r\n");
        bob.append("sat: 100\r\n");
        bob.append("name: My First Yeelight!\r\n");
        return bob.toString();
    }
}