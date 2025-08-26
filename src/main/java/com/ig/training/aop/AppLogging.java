package com.ig.training.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Aspect
//@Slf4j
public class AppLogging {



    @Around("@annotation(auditFilter) && execution(public * *(..))")
    public Object logging(ProceedingJoinPoint joinPoint, AuditFilter auditFilter) throws Throwable {
        var requestId = UUID.randomUUID().toString();
        Logger log = getLogger(joinPoint);
        log.info("[{}] ==> Request to controller start ", requestId);
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();

        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();

        log.info("[{}] ==> Execution Time Log: Class: {}, Method: {}, Time Taken: {} ms",
                requestId,
                className, methodName, (endTime - startTime));

        return result;
    }


    private Logger getLogger(ProceedingJoinPoint joinPoint) {
        return LoggerFactory.getLogger(joinPoint.getTarget().getClass());
    }
}
