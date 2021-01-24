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
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
        return broadcastMessageV2(message, request.getIpList());
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

    private List<Map<String, Object>> broadcastMessageV2(
            String message,
            List<String> ip_list
    ) {
        int threadCount = Integer.min(ip_list.size(), 10);
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        try {
            Future<String>[] messageThreads = new Future[ip_list.size()];
            for (int i = 0; i < ip_list.size(); i++) {
                final String ip = ip_list.get(i);
                messageThreads[i] = executor.submit(() -> {
                    try {
                        return sendMessage(ip, message, 3);
                    } catch (Exception err) {
                        log.error("Could not send message to " + ip, err);
                        return "???";
                    }

                });
            }

            long total = 0;
            long sleep = 100;
            while (!allDone(messageThreads) && total < 5000) {
                try {
                    Thread.sleep(sleep);
                    total += sleep;
                } catch (Exception err) {
                }
            }

            List<Map<String, Object>> responses = new ArrayList<>();
            for (int i = 0; i < messageThreads.length; i++) {
                if (!messageThreads[i].isDone()) {
                    messageThreads[i].cancel(true);
                    continue;
                }
                String ip = ip_list.get(i);
                String response = messageThreads[i].get();
                JSONObject jsonObject = new JSONObject(response);
                jsonObject.put("ip", ip);
                responses.add(jsonObject.toMap());
            }

            return responses;
        } catch (Exception err) {
            throw new RuntimeException(err);
        }
    }

    private boolean allDone(Future<?>[] items) {
        for (Future<?> f : items) {
            if (!f.isDone())
                return false;
        }
        return true;
    }
}
