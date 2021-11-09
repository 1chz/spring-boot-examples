package io.github.shirohoo.springaop.advisor;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

// 순서를 보장하는 어드바이저
// 기본적으로 @Aspect는 순서를 보장하지 않는다
// @Order를 사용하면 순서를 보장할 수 있으나 메서드 단위에는 적용이 되지 않는다
// 따라서 포인트컷 1, 어드바이스 1로 이루어진 어드바이저 클래스를 따로 만들고 @Order를 작성해야 한다
@Slf4j
public class OrderedAdvisor {

    @Aspect
    @Order(1) // 가장 처음 실행될 어드바이저
    public static class ControllerAdvisor {

        @Around("io.github.shirohoo.springaop.pointcut.Pointcuts.controllers()")
        public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("ControllerAdvisor 실행");
            return joinPoint.proceed();
        }

    }

    @Aspect
    @Order(2) // 두번째로 실행될 어드바이저
    public static class ServiceAdvisor {

        @Around("io.github.shirohoo.springaop.pointcut.Pointcuts.services()")
        public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("ServiceAdvisor 실행");
            return joinPoint.proceed();
        }

    }

}
