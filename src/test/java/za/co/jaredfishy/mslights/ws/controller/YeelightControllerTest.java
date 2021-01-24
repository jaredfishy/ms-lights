package za.co.jaredfishy.mslights.ws.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import za.co.jaredfishy.mslights.application.domain.yeelight.YeelightCommandRequest;
import za.co.jaredfishy.mslights.application.service.YeelightService;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(YeelightController.class)
public class YeelightControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private YeelightService yeelightService;

    @Test
    public void testSendCommand() throws Exception {

        YeelightCommandRequest request = new YeelightCommandRequest(
                new ArrayList<>(),
                182,
                "test",
                Arrays.asList("yay")
        );

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", "ok");
        List<Map<String, Object>> response = Arrays.asList(jsonObject.toMap());

        given(yeelightService.send(Mockito.any(YeelightCommandRequest.class))).willReturn(response);

        mvc.perform(post("/yeelight/command")
                .content(asJsonString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].result", is("ok")))
        ;

    }

    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}