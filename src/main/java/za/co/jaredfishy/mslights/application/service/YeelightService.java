package za.co.jaredfishy.mslights.application.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import za.co.jaredfishy.mslights.application.domain.yeelight.YeelightCommandRequest;
import za.co.jaredfishy.mslights.application.util.YeelightRawCommandMessageCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class YeelightService {

    public List<Map<String, Object>> send(YeelightCommandRequest request) {
        String message = YeelightRawCommandMessageCreator.getMessage(request);
        return broadcastMessage(message, request.getIpList());
    }


    private List<Map<String, Object>> broadcastMessage(String message, List<String> ip_list) {
        LightConnectionHandler lightConnectionHandler = LightConnectionHandler.getInstance();
        List<Map<String, Object>> responses = new ArrayList<>();
        for (String ip : ip_list) {
            LightConnection lightConnection = lightConnectionHandler.getConnection(ip);
            JSONObject jsonObject = new JSONObject(lightConnection.send(message));
            jsonObject.put("ip", ip);
            responses.add(jsonObject.toMap());
        }
        return responses;
    }
}
