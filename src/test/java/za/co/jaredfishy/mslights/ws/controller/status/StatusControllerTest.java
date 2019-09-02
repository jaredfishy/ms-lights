package za.co.jaredfishy.mslights.ws.controller.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import za.co.jaredfishy.mslights.application.domain.StatusResponse;
import za.co.jaredfishy.mslights.application.service.status.StatusService;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StatusController.class)
public class StatusControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StatusService statusService;

    @Test
    public void testGetStatus() throws Exception {

        given(statusService.getStatus()).willReturn(new StatusResponse(
                LocalDateTime.of(2019, 12, 31, 17, 45, 32),
                3
        ));

        mvc.perform(get("/service/status")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$.timestamp", is("2019-12-31T17:45:32")))
                .andExpect(jsonPath("$.connectionCount", is(3)))
        ;
    }
    @Test
    public void testRefresh() throws Exception {

        //given(statusService.refresh()).willReturn();

        mvc.perform(get("/service/refresh")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        ;
    }
}