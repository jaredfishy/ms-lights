package za.co.jaredfishy.mslights.application.util;

import org.junit.Assert;
import org.junit.Test;

public class RGBCalculatorTest {

    @Test
    public void testWhite() {
        Assert.assertEquals(16777215, RGBCalculator.getColor(255, 255, 255));
    }

    @Test
    public void testBlue() {
        Assert.assertEquals(255, RGBCalculator.getColor(0, 0, 255));
    }
}