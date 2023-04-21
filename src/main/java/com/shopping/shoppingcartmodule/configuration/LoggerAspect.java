package com.shopping.shoppingcartmodule.configuration;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.springframework.stereotype.Component;

import java.util.Calendar;

@Aspect
@Slf4j
@Component
public class LoggerAspect {
    @Around(value="execution(* com.shopping.shoppingcartmodule.Service.Impl.*.*(..))")
    public Object aroundLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String methodName=proceedingJoinPoint.getSignature().getName();
        long startingTime = Calendar.getInstance().getTimeInMillis();
        log.info("Entering... to  method : {}", methodName );
        Object proceed = proceedingJoinPoint.proceed();
        long timeElapsed=Calendar.getInstance().getTimeInMillis()-startingTime;
        log.info("TimeDuration TotalTime:{}",timeElapsed);

        return proceed;

    }
}
