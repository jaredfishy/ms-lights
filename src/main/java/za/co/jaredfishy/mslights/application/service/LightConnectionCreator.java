package za.co.jaredfishy.mslights.application.service;

import org.springframework.stereotype.Component;

@Component
public class LightConnectionCreator {

    private static final int PORT = 55443;

    public LightConnectionV2 createConnection(String ip) {
        return new LightConnectionV2(ip, PORT);
    }
}