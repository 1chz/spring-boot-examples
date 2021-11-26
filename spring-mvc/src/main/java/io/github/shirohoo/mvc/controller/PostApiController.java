package io.github.shirohoo.mvc.controller;

import io.github.shirohoo.mvc.model.Cat;
import io.github.shirohoo.mvc.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PostApiController {

    @PostMapping("/v1/hello")
    public String helloV1(
        @RequestParam String name,
        @RequestParam int age,
        @RequestBody Person person
    ) {
        log.info("name={}, age={}", name, age);
        log.info("person={}", person);
        return "ok";
    }

    @PostMapping("/v2/hello")
    public String helloV2(@RequestBody Cat cat) {
        log.info("cat={}", cat);
        return "ok";
    }

}
