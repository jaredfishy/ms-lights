package za.co.jaredfishy.mslights.ws.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import za.co.jaredfishy.mslights.application.domain.PokeResponse;
import za.co.jaredfishy.mslights.application.domain.StatusResponse;
import za.co.jaredfishy.mslights.application.service.StatusService;

@RestController
public class StatusController {

    private StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping("/poke")
    @ResponseStatus(HttpStatus.OK)
    public PokeResponse poke() {
        return statusService.poke();
    }

    @GetMapping(value = "/service/status", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public StatusResponse getStatus() {
        return statusService.getStatus();
    }

    @GetMapping(value = "/service/refresh")
    public void refresh(){
        statusService.refresh();
    }

}
