package za.co.jaredfishy.mslights.ws.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {


    @GetMapping("/poke")
    @ResponseStatus(HttpStatus.OK)
    public String poke() {
        return "That tickles!";
    }
}
