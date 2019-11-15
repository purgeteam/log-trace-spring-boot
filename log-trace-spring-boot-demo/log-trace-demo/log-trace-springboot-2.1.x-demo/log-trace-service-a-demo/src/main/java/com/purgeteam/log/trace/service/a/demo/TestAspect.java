package com.purgeteam.log.trace.service.a.demo;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author purgeyao
 * @since 1.0
 */
@Slf4j
@Aspect
@Component
public class TestAspect {

    @Around("execution(public * com.purgeteam.log.trace.service.a.demo.TestService.test())")
    public Object fun1(ProceedingJoinPoint point) throws Throwable {
        // ...(方法执行前的逻辑)
        Object result = point.proceed();
        // ...(方法执行后的逻辑)
        log.info("切面");
        return result;
    }
}
