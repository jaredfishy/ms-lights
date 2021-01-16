package za.co.jaredfishy.mslights.application.util;

import org.json.JSONArray;
import org.json.JSONObject;
import za.co.jaredfishy.mslights.application.domain.yeelight.YeelightCommandRequest;

public class YeelightRawCommandMessageCreator {

    public static String getMessage(YeelightCommandRequest yeelightCommandRequest) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", yeelightCommandRequest.getId());
        jsonObject.put("method", yeelightCommandRequest.getMethod());
        JSONArray jsonArray = new JSONArray(yeelightCommandRequest.getParams());
        jsonObject.put("params", jsonArray);
        return jsonObject.toString();
    }

}
