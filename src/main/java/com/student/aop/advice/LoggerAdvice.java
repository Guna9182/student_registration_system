package com.student.aop.advice;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAdvice {

    Logger myLogger = LoggerFactory.getLogger(LoggerAdvice.class);

    @Pointcut(value = "execution(* com.student.*.*.*(..))")
    public void myPointJoinForAllMethods(){
    }

    @Pointcut("execution(* com.student.service.*.*(..))")
    public void businessLayerExecution(){
    }

    @Pointcut("execution(* com.student.exceptions.*.*(..))")
    public void handleExceptions(){
    }

    @Before("myPointJoinForAllMethods()")
    public void before(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        String className = jp.getTarget().getClass().toString();
        myLogger.info("method invoked :: " + className +":" + methodName +"()" +"arguments :");
    }

    @AfterReturning(value = "businessLayerExecution()", returning = "result")
    public void afterReturning(JoinPoint jp, Object result) {

        myLogger.info("After returning :: {} returned with value {}", jp, result);
    }

    @After(value = "handleExceptions()")
    public void afterThrowingException(JoinPoint jp){
        String methodName = jp.getSignature().getName();
        String className = jp.getTarget().getClass().toString();

        myLogger.warn("After Exceptions :: " + className +":" + methodName +"()" +"arguments :");
    }

}
