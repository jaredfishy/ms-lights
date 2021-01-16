package za.co.jaredfishy.mslights.application.util;

import org.junit.Assert;
import org.junit.Test;
import za.co.jaredfishy.mslights.application.domain.FormattedDateTime;
import za.co.jaredfishy.mslights.application.domain.light.LightLocation;
import za.co.jaredfishy.mslights.application.domain.light.LightStatus;

import java.time.LocalDateTime;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;

public class LightStatusBuilderTest {

    @Test
    public void buildFromYeelightResponse() {
        FormattedDateTime timestamp = FormattedDateTime.of(LocalDateTime.now());
        LightStatus actual = LightStatusBuilder.build(DummyDataUtil.getYeelightResponse(timestamp));
        LightStatus expected = getExpected(timestamp);
        Assert.assertThat(expected, sameBeanAs(actual));
    }

    private LightStatus getExpected(FormattedDateTime timestamp) {
        return new LightStatus(
                timestamp,
                true,
                100,
                "2",
                3500,
                16711680,
                359,
                100
                );
    }
}