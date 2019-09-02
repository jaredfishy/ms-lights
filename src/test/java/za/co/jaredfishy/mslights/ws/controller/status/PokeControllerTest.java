package za.co.jaredfishy.mslights.ws.controller.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import za.co.jaredfishy.mslights.application.domain.PokeResponse;
import za.co.jaredfishy.mslights.application.service.status.PokeService;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PokeController.class)
public class PokeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PokeService pokeService;

    @Test
    public void testPoke() throws Exception {

        given(pokeService.poke()).willReturn(new PokeResponse("yay"));

        mvc.perform(get("/poke")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("yay")));
    }

}