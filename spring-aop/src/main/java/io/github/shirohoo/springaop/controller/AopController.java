package io.github.shirohoo.springaop.controller;

import io.github.shirohoo.springaop.service.AopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AopController {

    private final AopService service;

    @GetMapping
    public String index() {
        log.info("AopController 실행");
        service.execute();
        return "index";
    }

}
