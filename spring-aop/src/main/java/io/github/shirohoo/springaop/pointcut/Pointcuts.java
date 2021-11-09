package io.github.shirohoo.springaop.pointcut;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    // io.github.shirohoo.springaop.controller 패키지와 하위 패키지
    @Pointcut("execution(* io.github.shirohoo.springaop.controller..*(..))")
    public void controllers(){
    }

    // 모든 패키지에 존재하는, 이름패턴이 *Service 인 클래스 대상
    @Pointcut("execution(* *..*Service.*(..))")
    public void services(){
    }

    @Pointcut("controllers() && services()")
    public void controllersAndServices(){
    }

}
