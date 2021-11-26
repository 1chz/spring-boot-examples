package io.github.shirohoo.mvc.controller;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

@WebMvcTest(PostApiController.class)
class PostApiControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    @DisplayName("Post 요청도 URL 헤더에 있는 데이터를 @RequestParam을 통해 받을 수 있다")
    void helloV1() throws Exception {
        performPost("v1");
    }

    @Test
    @DisplayName("@RequestBody는 기본 생성자가 없으면 예외를 던진다")
    void helloV2_exception() throws Exception {
        assertThatThrownBy(() -> performPost("v2")).isInstanceOf(NestedServletException.class);
    }

    private void performPost(String version) throws Exception {
        String content = "{\"name\":\"nameInHttpBody\",\"age\":11}";
        mvc.perform(post("/" + version + "/hello?name=urlName&age=50")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
            .andDo(print())
            .andExpect(status().isOk());
    }

}
