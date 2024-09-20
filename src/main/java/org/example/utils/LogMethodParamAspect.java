package org.example.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogMethodParamAspect {
    @After("@annotation(com.example.util.LogMethodParam)")
    public void logMethodParameters(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println("Method parameter: " + arg);
        }
    }
}
