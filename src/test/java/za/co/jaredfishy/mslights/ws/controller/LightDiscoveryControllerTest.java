package za.co.jaredfishy.mslights.ws.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import za.co.jaredfishy.mslights.application.domain.light.Light;
import za.co.jaredfishy.mslights.application.service.LightDiscoveryService;
import za.co.jaredfishy.mslights.application.util.DummyLight;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(LightDiscoveryController.class)
public class LightDiscoveryControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LightDiscoveryService lightDiscoveryService;

    @Test
    public void testDiscover() throws Exception {

        List<Light> lights = new ArrayList<>();
        lights.add(DummyLight.get(1));

        given(lightDiscoveryService.discover()).willReturn(lights);

        mvc.perform(get("/light/discover")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].size()", is(5)))
                .andExpect(jsonPath("$[0].id", is("0x0000000007f16b5c")))
                .andExpect(jsonPath("$[0].model", is("color")))
//                .andExpect(jsonPath("$[0].firmwareVersion", is("26")))
//                .andExpect(jsonPath("$[0].support", is("set_power set_rgb jaredfishy")))
                .andExpect(jsonPath("$[0].on", is(true)))
                .andExpect(jsonPath("$[0].name", is("My Bulb")))
                .andExpect(jsonPath("$[0].location.ip", is("192.168.0.100")))
        ;
    }
}