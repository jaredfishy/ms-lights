package za.co.jaredfishy.mslights.application.service.status;

import org.springframework.stereotype.Service;
import za.co.jaredfishy.mslights.application.domain.FormattedDateTime;
import za.co.jaredfishy.mslights.application.domain.StatusResponse;

@Service
public class StatusService {

    public StatusResponse getStatus() {
        return new StatusResponse(
                FormattedDateTime.now()
        );
    }

}
