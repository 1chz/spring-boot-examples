package io.github.shirohoo.springaop;

import static org.assertj.core.api.Assertions.*;
import io.github.shirohoo.springaop.advisor.OrderedAdvisor.ControllerAdvisor;
import io.github.shirohoo.springaop.advisor.OrderedAdvisor.ServiceAdvisor;
import io.github.shirohoo.springaop.controller.AopController;
import io.github.shirohoo.springaop.service.AopService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import({
    ControllerAdvisor.class,
    ServiceAdvisor.class,
})
@SpringBootTest
class SpringAopApplicationTests {

    @Autowired
    private AopController controller;

    @Autowired
    private AopService service;

    @Test
    void contextLoads() {
        controller.index();
        assertThat(AopUtils.isAopProxy(controller)).isTrue();
        assertThat(AopUtils.isAopProxy(service)).isTrue();
    }

}
