package za.co.jaredfishy.mslights.application.service;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import za.co.jaredfishy.mslights.application.domain.yeelight.YeelightCommandRequest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class YeelightServiceTest {

    private YeelightService yeelightService;

    @Before
    public void setUp()  {
        yeelightService = new YeelightService(new LightConnectionCreator());
    }

    @Ignore
    @Test
    public void testSpam() {
        List<String> ipList = Collections.singletonList("192.168.0.106");
        YeelightCommandRequest onCommand = new YeelightCommandRequest(
                ipList,
                1,
                "set_power",
                Arrays.asList("on", "smooth", 250)
        );
        YeelightCommandRequest offCommand = new YeelightCommandRequest(
                ipList,
                1,
                "set_power",
                Arrays.asList("off", "smooth", 250)
        );

        long delay = 500;
        for (int i = 0; i < 10; i++) {
            yeelightService.send(onCommand);
            try {
                Thread.sleep(delay);
            } catch (Exception err) {
            }
            yeelightService.send(offCommand);
            try {
                Thread.sleep(delay);
            } catch (Exception err) {
            }
        }

    }

}