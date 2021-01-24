package za.co.jaredfishy.mslights.application.util;

import org.junit.Assert;
import org.junit.Test;
import za.co.jaredfishy.mslights.application.domain.FormattedDateTime;
import za.co.jaredfishy.mslights.application.domain.light.LightLocation;

import java.time.LocalDateTime;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;

public class LightLocationBuilderTest {

    @Test
    public void parse() {
        FormattedDateTime timestamp = DummyDataUtil.getTimestamp();
        LightLocation actual = LightLocationBuilder.build(DummyDataUtil.getYeelightResponse(timestamp));
        LightLocation expected = getExpected(timestamp);
        Assert.assertThat(expected, sameBeanAs(actual));
    }

    private LightLocation getExpected(FormattedDateTime timestamp) {
        return new LightLocation(timestamp, "192.168.0.100", 55443);
    }
}