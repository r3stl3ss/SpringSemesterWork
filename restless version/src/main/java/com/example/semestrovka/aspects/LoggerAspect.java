package com.example.semestrovka.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Aspect
@Component
public class LoggerAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(public * com.example.semestrovka.services.implemented.*.*(..))")
    public void callAtMyServicePublic() {
    }

    @Before("callAtMyServicePublic()")
    public void beforeCallAtMethod1(JoinPoint jp) {
        Date date = new Date();
        logger.info("Before - " + jp.getSignature()+ "; Args - " + Arrays.toString(jp.getArgs()) +"; time - " + date);
    }

    @After("callAtMyServicePublic()")
    public void afterCallAt(JoinPoint jp) {
        Date date = new Date();
        logger.info("After - " + jp.getSignature()+ "; Args - " + Arrays.toString(jp.getArgs()) + "; time - " + date);
    }
}