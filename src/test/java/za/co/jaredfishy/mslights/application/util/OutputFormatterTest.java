package za.co.jaredfishy.mslights.application.util;

import org.junit.Assert;
import org.junit.Test;

public class OutputFormatterTest {

    @Test
    public void formatOutput() {

        String inputString = "a\r\nb\r\nc";
        String actual = OutputFormatter.formatOutput(inputString);
        String expected = "a b c";
        Assert.assertEquals(expected, actual);

    }
}