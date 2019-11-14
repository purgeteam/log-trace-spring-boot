package com.purgeteam.log.sleuth.demo;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author purgeyao
 * @since 1.0
 */
@Slf4j
@RestController
public class TestController {

    @Resource
    private TestService testService;

    @GetMapping("test")
    public String test() {
        MDC.put("test", String.valueOf(Math.random()));
        log.info("controller test 执行 {}", MDC.get("test"));
        testService.test();
        return "test";
    }
}
