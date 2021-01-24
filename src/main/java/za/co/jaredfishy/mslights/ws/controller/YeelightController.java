package za.co.jaredfishy.mslights.ws.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import za.co.jaredfishy.mslights.application.domain.yeelight.YeelightCommandRequest;
import za.co.jaredfishy.mslights.application.service.YeelightService;

import java.util.ArrayList;
import java.util.Arrays;
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

//    @GetMapping("/test")
//    public Object test(@RequestParam("state") String state){
//        List<Object> params = new ArrayList<>();
//        params.add(state);
//        params.add("sudden");
//        params.add(0);
//        YeelightCommandRequest yeelightCommandRequest = new YeelightCommandRequest(
//                Arrays.asList(
//                        "192.168.0.100",
//                        "192.168.0.101",
//                        "192.168.0.102",
//                        "192.168.0.103",
//                        "192.168.0.104",
//                        "192.168.0.105",
//                        "192.168.0.106",
//                        "192.168.0.107",
//                        "192.168.0.108",
//                        "192.168.0.109"
//                ),
//                1,
//                "set_power",
//                params
//        );
//        return yeelightService.send(yeelightCommandRequest);
//    }
}