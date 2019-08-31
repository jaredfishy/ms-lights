package za.co.jaredfishy.mslights.ws.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import za.co.jaredfishy.mslights.application.domain.LightLocation;
import za.co.jaredfishy.mslights.application.service.LightService;

import java.util.List;

@RestController
@RequestMapping("/light")
public class LightController {

    private LightService lightService;

    public LightController(LightService lightService) {
        this.lightService = lightService;
    }

    @PostMapping(path = "/on", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void turnOn(@RequestBody List<LightLocation> lightLocations) {
        lightService.turnOn(lightLocations);
    }

    @PostMapping(path = "/off", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void turnOff(@RequestBody List<LightLocation> lightLocations) {
        lightService.turnOff(lightLocations);
    }

    @PostMapping(path = "/color", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void update(
            @RequestParam(value = "r", defaultValue = "0") int red,
            @RequestParam(value = "g", defaultValue = "0") int green,
            @RequestParam(value = "b", defaultValue = "0") int blue,
            @RequestBody List<LightLocation> lightLocations
    ) {
        lightService.setColor(lightLocations, red, green, blue);
    }
}
