package za.co.jaredfishy.mslights.application.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import za.co.jaredfishy.mslights.application.domain.yeelight.YeelightCommandRequest;
import za.co.jaredfishy.mslights.application.util.YeelightRawCommandMessageCreator;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class YeelightService {

    private final Logger log;
    private final LightConnectionCreator lightConnectionCreator;

    public YeelightService(LightConnectionCreator lightConnectionCreator) {
        this.lightConnectionCreator = lightConnectionCreator;
        this.log = LogManager.getLogger(getClass());
    }

    public List<Map<String, Object>> send(YeelightCommandRequest request) {
        String message = YeelightRawCommandMessageCreator.getMessage(request);
        return broadcastMessage(message, request.getIpList());
    }


    private List<Map<String, Object>> broadcastMessage(
            String message,
            List<String> ip_list
    ) {
        List<Map<String, Object>> responses = new ArrayList<>();
        // todo: on batched calls, retry failed ones afterward
        for (String ip : ip_list) {
            try {
                String response = sendMessage(ip, message, 3);
                JSONObject jsonObject = new JSONObject(response);
                jsonObject.put("ip", ip);
                responses.add(jsonObject.toMap());
            } catch (Exception err) {
                log.error("Could not send message to " + ip, err);
            }
        }
        return responses;
    }

    private String sendMessage(
            String ip,
            String message,
            int retries
    ) {
        LightConnectionV2 lightConnection = lightConnectionCreator.createConnection(ip);
        try {
            return lightConnection.send(message);
        } catch (SocketTimeoutException socketException) {
            if (retries > 0) {
                log.info("Failed to send message to:  " + ip + " - retrying...");
                return sendMessage(ip, message, retries - 1);
            }
        }
        throw new RuntimeException("Could not send message to " + ip);
    }
}
