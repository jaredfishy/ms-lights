package za.co.jaredfishy.mslights.application.util;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import za.co.jaredfishy.mslights.application.domain.YeelightCommandRequest;

import java.util.ArrayList;
import java.util.List;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;

public class YeelightRawCommandMessageCreatorTest {

    @Test
    public void getMessage() {

        List<Object> params = new ArrayList<>();
        params.add("power");
        params.add("not_exist");
        params.add("bright");
        YeelightCommandRequest commandRequest = new YeelightCommandRequest(null, 7, "get_prop", params);

        String rawCommand = YeelightRawCommandMessageCreator.getMessage(commandRequest);
        String expectedCommand = "{\"id\":7, \"method\":\"get_prop\", params:[\"power\", \"not_exist\", \"bright\"]}";

        JSONObject actual = new JSONObject(rawCommand);
        JSONObject expected = new JSONObject(expectedCommand);

        Assert.assertThat(actual, sameBeanAs(expected));

    }
}