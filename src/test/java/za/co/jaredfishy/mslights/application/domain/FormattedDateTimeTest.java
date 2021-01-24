package za.co.jaredfishy.mslights.application.domain;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class FormattedDateTimeTest {

    @Ignore
    @Test
    public void testToString(){
        for(int i=0;i<100;i++) {
            FormattedDateTime dt = FormattedDateTime.now();
            System.out.println(dt);
        }
    }

}