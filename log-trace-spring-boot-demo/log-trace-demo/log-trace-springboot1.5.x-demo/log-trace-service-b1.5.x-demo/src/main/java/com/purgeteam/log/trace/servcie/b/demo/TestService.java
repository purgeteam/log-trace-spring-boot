package com.purgeteam.log.trace.servcie.b.demo;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

/**
 * @author purgeyao
 * @since 1.0
 */
@Slf4j
@Service
public class TestService {

    public void test() {
        log.info("test 方法执行 {}", MDC.get("X-B3-TraceId"));
        test1();
    }

    private void test1() {
        log.info("test1 方法执行 {}", MDC.get("X-B3-TraceId"));
        new Thread(() -> log.info("test1 方法 线程执行 {}", MDC.get("X-B3-TraceId"))).start();
    }
}
