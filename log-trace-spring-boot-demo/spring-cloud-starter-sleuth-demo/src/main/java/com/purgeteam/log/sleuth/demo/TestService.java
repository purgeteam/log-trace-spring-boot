package com.purgeteam.log.sleuth.demo;

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
        log.info("test 方法执行 {}", MDC.get("test"));
        test1();
    }

    private void test1() {
        log.info("test1 方法执行 {}", MDC.get("test"));
    }
}
