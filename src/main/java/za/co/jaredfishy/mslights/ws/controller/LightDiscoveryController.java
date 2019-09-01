package za.co.jaredfishy.mslights.ws.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import za.co.jaredfishy.mslights.application.domain.light.Light;
import za.co.jaredfishy.mslights.application.service.LightDiscoveryService;

import java.util.Map;

@RestController
@RequestMapping("/light")
public class LightDiscoveryController {

    private LightDiscoveryService lightDiscoveryService;

    public LightDiscoveryController(LightDiscoveryService lightDiscoveryService) {
        this.lightDiscoveryService = lightDiscoveryService;
    }

    @GetMapping(path = "/discover", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Light> discover() {
        return lightDiscoveryService.discover();
    }
}
