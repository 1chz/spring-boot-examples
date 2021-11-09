package io.github.shirohoo.springaop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AopService {

    public void execute() {
        log.info("AopService 실행");
    }

}
