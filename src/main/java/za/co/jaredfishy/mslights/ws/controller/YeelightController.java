package za.co.jaredfishy.mslights.ws.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.jaredfishy.mslights.application.domain.yeelight.YeelightCommandRequest;
import za.co.jaredfishy.mslights.application.service.YeelightService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/yeelight")
public class YeelightController {

    private YeelightService yeelightService;

    public YeelightController(YeelightService yeelightService) {
        this.yeelightService = yeelightService;
    }

    @PostMapping(value = "/command", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Map<String, Object>> sendCommands(@RequestBody YeelightCommandRequest yeelightCommandRequest) {
        return yeelightService.send(yeelightCommandRequest);
    }
}