package za.co.jaredfishy.mslights.application.service.status;

import org.springframework.stereotype.Service;
import za.co.jaredfishy.mslights.application.domain.StatusResponse;
import za.co.jaredfishy.mslights.application.service.LightConnectionHandler;

import java.time.LocalDateTime;

@Service
public class StatusService {

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
