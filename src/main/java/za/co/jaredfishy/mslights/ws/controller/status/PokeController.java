package za.co.jaredfishy.mslights.ws.controller.status;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import za.co.jaredfishy.mslights.application.domain.PokeResponse;
import za.co.jaredfishy.mslights.application.service.status.PokeService;

@RestController
public class PokeController {

    private PokeService pokeService;

    public PokeController(PokeService pokeService) {
        this.pokeService = pokeService;
    }

    @GetMapping("/poke")
    @ResponseStatus(HttpStatus.OK)
    public PokeResponse poke() {
        return pokeService.poke();
    }
}
