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
import za.co.jaredfishy.mslights.application.util.DummyDataUtil;
import za.co.jaredfishy.mslights.application.util.DummyLight;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNotNull;
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

        LocalDateTime timestamp = LocalDateTime.of(2020,12,21,13,37,00);
        List<Light> lights = DummyDataUtil.getDiscoveryData(timestamp);

        given(lightDiscoveryService.discover()).willReturn(lights);

        mvc.perform(get("/light/discover")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].size()", is(6)))
                .andExpect(jsonPath("$[0].id", is("0x0000000007f16b5c")))
                .andExpect(jsonPath("$[0].brand", is("YEELIGHT")))
                .andExpect(jsonPath("$[0].model", is("color")))
                .andExpect(jsonPath("$[0].name", is("My First Yeelight!")))

                .andExpect(jsonPath("$[0].status.size()", is(8)))
                .andExpect(jsonPath("$[0].status.powered", is(true)))
                .andExpect(jsonPath("$[0].status.bright", is(100)))
                .andExpect(jsonPath("$[0].status.colorMode", is("2")))
                .andExpect(jsonPath("$[0].status.ct", is(3500)))
                .andExpect(jsonPath("$[0].status.rgb", is(16711680)))
                .andExpect(jsonPath("$[0].status.hue", is(359)))
                .andExpect(jsonPath("$[0].status.sat", is(100)))

                .andExpect(jsonPath("$[0].location.size()", is(3)))
                .andExpect(jsonPath("$[0].location.ip", is("192.168.0.100")))
                .andExpect(jsonPath("$[0].location.port", is(55443)))
        ;
    }
}