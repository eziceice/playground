package com.springboot.bet.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.jboss.logging.NDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Aspect for bet controller logger
 */
@Aspect
public class BetControllerLogger {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public BetControllerLogger() {

    }

    @Before("execution(* com.springboot.bet.controller.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        StringBuilder sb = new StringBuilder();
        for (Object o : args) {
            if (o instanceof HttpServletRequest) {
                HttpServletRequest httpServletRequest = (HttpServletRequest) o;
                String message = "Request Session Id: " + httpServletRequest.getRequestedSessionId();
                NDC.push(message);
                break;
            }
            sb.append(o.toString());
        }
        log.info("Receiving request for " + joinPoint.getSignature().getName() + ", request is " + sb.toString() + ", start processing...");
    }

    @AfterReturning("execution(* com.springboot.bet.controller.*.*(..))")
    public void logAfterReturning(JoinPoint joinPoint) {
        log.info("Successfully completed action for " + joinPoint.getSignature().getName());
        NDC.clear();
    }

    @AfterThrowing("execution(* com.springboot.bet.controller.*.*(..))")
    public void logAfterThrowing(JoinPoint joinPoint) {
        NDC.clear();
    }
}
