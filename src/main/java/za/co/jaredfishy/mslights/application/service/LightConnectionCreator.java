package za.co.jaredfishy.mslights.application.service;

import org.springframework.stereotype.Component;

@Component
public class LightConnectionCreator {
    public LightConnection createConnection(String ip) {
        return new LightConnection(ip);
    }
}