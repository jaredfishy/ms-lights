package za.co.jaredfishy.mslights.application.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import za.co.jaredfishy.mslights.application.domain.YeelightCommandRequest;
import za.co.jaredfishy.mslights.application.util.YeelightRawCommandMessageCreator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class YeelightService {

    public String send(YeelightCommandRequest request) {
        String message = YeelightRawCommandMessageCreator.getMessage(request);
        return broadcast(message, Arrays.asList(request.getIp())).get(0).toString();
    }

    public String sendMultiple(YeelightCommandRequest request) {
        String message = YeelightRawCommandMessageCreator.getMessage(request);
        List<JSONObject> responses = broadcast(message, request.getIpList());
        JSONArray jsonArray = new JSONArray(responses);
        return jsonArray.toString();
    }


    private List<JSONObject> broadcast(String message, List<String> ip_list) {
        LightConnectionHandler lightConnectionHandler = LightConnectionHandler.getInstance();
        List<JSONObject> responses = new ArrayList<>();
        for (String ip : ip_list) {
            LightConnection lightConnection = lightConnectionHandler.getConnection(ip);
            JSONObject jsonObject = new JSONObject(lightConnection.send(message));
            jsonObject.put("ip", ip);
            responses.add(jsonObject);
        }
        return responses;
    }
}
