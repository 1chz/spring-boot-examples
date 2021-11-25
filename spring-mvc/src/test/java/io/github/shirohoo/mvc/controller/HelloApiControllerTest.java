package io.github.shirohoo.mvc.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(HelloApiController.class)
class HelloApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("@ModelAttribute가 존재하지 않는 경우")
    void helloV1() throws Exception {
        performGet("v1");
    }

    @Test
    @DisplayName("@ModelAttribute가 존재하는 경우")
    void helloV2() throws Exception {
        performGet("v2");
    }

    @Test
    @DisplayName("@RequestParam이 존재하는 경우")
    void helloV3() throws Exception {
        performGet("v3");
    }

    @Test
    @DisplayName("@RequestParam이 존재하지 않는 경우")
    void helloV4() throws Exception {
        performGet("v4");
    }

    private void performGet(String version) throws Exception {
        mvc.perform(get("/" + version + "/hello?name=siro&age=11"))
            .andDo(print())
            .andExpect(status().isOk());
    }

}
