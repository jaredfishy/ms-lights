package za.co.jaredfishy.mslights.application.service;

import org.springframework.stereotype.Service;
import za.co.jaredfishy.mslights.application.domain.PokeResponse;
import za.co.jaredfishy.mslights.application.domain.StatusResponse;

import java.time.LocalDateTime;

@Service
public class StatusService {

    public PokeResponse poke() {
        return new PokeResponse("Hey, that tickles!");
    }

    public StatusResponse getStatus() {
        return new StatusResponse(
                LocalDateTime.now(),
                LightConnectionHandler.getInstance().getConnectionCount()
        );
    }

    public void refresh() {
        LightConnectionHandler.reinitialise();
    }
}
