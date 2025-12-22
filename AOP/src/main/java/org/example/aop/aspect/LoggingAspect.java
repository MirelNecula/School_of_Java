package org.example.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {


    @Pointcut("within(org.example.aop..*)")
    private void inApp() {}


    @Before("inApp() && execution(* org.example.aop.service.BookService.getByTitle(..))")
    public void onGetByTitle(JoinPoint jp) {
        System.out.println("[AOP][execution] " + jp.getSignature());
    }


    @Around("inApp() && @annotation(org.springframework.web.bind.annotation.PostMapping)")
    public Object aroundPost(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("[AOP][@annotation] BEFORE " + pjp.getSignature());
        Object out = pjp.proceed();
        System.out.println("[AOP][@annotation] AFTER  " + pjp.getSignature());
        return out;
    }


    @After("inApp() && within(org.example.aop.controller..*)")
    public void afterAnyController(JoinPoint jp) {
        System.out.println("[AOP][within] controller call: " + jp.getSignature());
    }


    @Before("inApp() && args(java.lang.String, int)")
    public void whenStringIntArgs(JoinPoint jp) {
        System.out.println("[AOP][args] (String,int) -> " + jp.getSignature());
    }


    @Before("inApp() && this(org.example.aop.service.BookService)")
    public void proxyTypeIsBookService(JoinPoint jp) {
        System.out.println("[AOP][this] proxy is BookService -> " + jp.getSignature());
    }


    @AfterReturning("inApp() && target(org.example.aop.service.BookService)")
    public void realTargetIsBookService(JoinPoint jp) {
        System.out.println("[AOP][target] target is BookService -> " + jp.getSignature());
    }


    @Before("inApp() && @within(org.springframework.web.bind.annotation.RestController)")
    public void insideRestController(JoinPoint jp) {
        System.out.println("[AOP][@within] in @RestController -> " + jp.getSignature());
    }


    @After("inApp() && @target(org.springframework.stereotype.Service)")
    public void targetHasServiceAnnotation(JoinPoint jp) {
        System.out.println("[AOP][@target] target annotated @Service -> " + jp.getSignature());
    }


    @Before("inApp() && @args(org.springframework.validation.annotation.Validated)")
    public void argumentTypeAnnotatedValidated(JoinPoint jp) {
        System.out.println("[AOP][@args] arg type annotated @Validated -> " + jp.getSignature());
    }
}
