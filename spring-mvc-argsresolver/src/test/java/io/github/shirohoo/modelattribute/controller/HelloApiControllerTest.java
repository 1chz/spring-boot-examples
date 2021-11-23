package io.github.shirohoo.modelattribute.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
        mvc.perform(get("/v1/hello?name=siro&age=29"))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    void helloV2() throws Exception {
        mvc.perform(get("/v2/hello?name=siro&age=29"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("ok"));
    }

}
