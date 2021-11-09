package io.github.shirohoo.springaop.advisor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

// 어드바이저는 포인트컷 + 어드바이스
// 포인트컷 = 어드바이스를 적용 할 대상
// 어드바이스 = 부가기능
@Aspect
public class AopAdvisor {

    @Around("io.github.shirohoo.springaop.pointcut.Pointcuts.controllers()") // <- 포인트컷
    public Object doSomething_1(ProceedingJoinPoint joinPoint) throws Throwable { // <- 어드바이스
        return joinPoint.proceed();
    }

    @Around("io.github.shirohoo.springaop.pointcut.Pointcuts.controllersAndServices()") // <- 포인트컷
    public Object doSomething_2(ProceedingJoinPoint joinPoint) throws Throwable { // <- 어드바이스
        return joinPoint.proceed();
    }

    @Around("io.github.shirohoo.springaop.pointcut.Pointcuts.services()") // <- 포인트컷
    public Object doSomething_3(ProceedingJoinPoint joinPoint) throws Throwable { // <- 어드바이스
        return joinPoint.proceed();
    }
}
