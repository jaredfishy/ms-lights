package za.co.jaredfishy.mslights.ws.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.jaredfishy.mslights.application.domain.YeelightCommandRequest;
import za.co.jaredfishy.mslights.application.service.YeelightService;

@RestController
@RequestMapping("/yeelight")
public class YeelightController {

    private YeelightService yeelightService;

    public YeelightController(YeelightService yeelightService) {
        this.yeelightService = yeelightService;
    }

    @PostMapping(value = "/command", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String sendCommand(@RequestBody YeelightCommandRequest yeelightCommandRequest) {
        return yeelightService.send(yeelightCommandRequest);
    }

    @PostMapping(value = "/commands", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String sendCommands(@RequestBody YeelightCommandRequest yeelightCommandRequest) {
        return yeelightService.sendMultiple(yeelightCommandRequest);
    }
}