package za.co.jaredfishy.mslights.application.service.status;

import org.springframework.stereotype.Service;
import za.co.jaredfishy.mslights.application.domain.PokeResponse;

@Service
public class PokeService {

    public PokeResponse poke() {
        return new PokeResponse("Hey, that tickles:D");
    }
}
