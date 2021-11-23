package io.github.shirohoo.modelattribute.controller;

import io.github.shirohoo.modelattribute.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloApiController {

    @GetMapping("/v1/hello")
    public Person helloV1(Person person) {
        log.info("person={}", person);
        return person;
    }

    @GetMapping("/v2/hello")
    public String helloV2(@ModelAttribute Person person) {
        log.info("person={}", person);
        return "ok";
    }

}
