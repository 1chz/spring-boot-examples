package io.github.shirohoo.modelattribute.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(HelloApiController.class)
class HelloApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void helloV1() throws Exception {
        performGet("v1");
    }

    @Test
    void helloV2() throws Exception {
        performGet("v2");
    }

    @Test
    void helloV33() throws Exception {
        performGet("v3");
    }

    private void performGet(String version) throws Exception {
        mvc.perform(get("/" + version + "/hello?name=siro&age=11"))
            .andDo(print())
            .andExpect(status().isOk());
    }

}
