package za.co.jaredfishy.mslights.ws.controller.status;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import za.co.jaredfishy.mslights.application.domain.StatusResponse;
import za.co.jaredfishy.mslights.application.service.status.StatusService;

@RestController
@RequestMapping("/service")
public class StatusController {

    private StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping(value = "/status", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public StatusResponse getStatus() {
        return statusService.getStatus();
    }

}
