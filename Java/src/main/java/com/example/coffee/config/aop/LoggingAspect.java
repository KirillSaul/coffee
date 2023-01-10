package com.example.coffee.config.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Log4j2
@Component
@Aspect
public class LoggingAspect {

    private void loggingInfoSignatureBefore(JoinPoint joinPoint) {
        List<Object> methodParameters = Arrays.asList(joinPoint.getArgs().clone());
        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
        List<String> signature = new ArrayList<>();

        for (int i = 0; i < methodParameters.size(); i++) {
            signature.add(codeSignature.getParameterNames()[i] + " = " + (isNull(methodParameters.get(i)) ? null : methodParameters.get(i).toString()));
        }

        log.info("TRY TO USE IN " + joinPoint.getSignature().getDeclaringTypeName());

        log.info(codeSignature.getName() + signature.stream().collect(Collectors.joining(", ", "(", ")")));
    }

    private void loggingInfoSignatureAfter(JoinPoint joinPoint) {
        log.info("COMPLETED USING " + joinPoint.getSignature().getDeclaringTypeName());
    }

    private void loggingError(JoinPoint joinPoint, Throwable exception) {
        log.error("IN " + joinPoint.getSignature() + " " + exception);
    }

    @Before("execution(* com.example.coffee.controller.*.* (..))")
    public void beforeControllerAdvice(JoinPoint joinPoint) {
        loggingInfoSignatureBefore(joinPoint);
    }

    @Before("execution(* com.example.coffee.service.*.*.* (..))")
    public void beforeServiceAdvice(JoinPoint joinPoint) {
        loggingInfoSignatureBefore(joinPoint);
    }

    @AfterReturning("execution(* com.example.coffee.controller.*.* (..))")
    public void afterReturningControllerAdvice(JoinPoint joinPoint) {
        loggingInfoSignatureAfter(joinPoint);
    }

    @AfterReturning("execution(* com.example.coffee.service.*.*.* (..))")
    public void afterReturningServiceAdvice(JoinPoint joinPoint) {
        loggingInfoSignatureAfter(joinPoint);
    }

    @AfterThrowing(value = "execution(* com.example.coffee.controller.*.* (..))", throwing = "exception")
    public void afterThrowingControllerAdvice(JoinPoint joinPoint, Throwable exception) {
        loggingError(joinPoint, exception);
    }

    @AfterThrowing(value = "execution(* com.example.coffee.service.*.*.* (..))", throwing = "exception")
    public void afterThrowingServiceAdvice(JoinPoint joinPoint, Throwable exception) {
        loggingError(joinPoint, exception);
    }
}
