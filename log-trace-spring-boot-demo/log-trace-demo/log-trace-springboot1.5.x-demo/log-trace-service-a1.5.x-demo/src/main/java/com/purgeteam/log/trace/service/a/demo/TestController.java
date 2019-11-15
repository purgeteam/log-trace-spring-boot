package com.purgeteam.log.trace.service.a.demo;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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

    @Resource
    private TestFeign testFeign;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("test")
    public String test() {
//        MDC.put("X-B3-TraceId", String.valueOf(Math.random()));
        log.info("controller test 执行 {}", MDC.get("X-B3-TraceId"));
        testService.test();
        return "test";
    }

    @GetMapping("test1")
    public String test1() {
        log.info("controller test1 执行 {}", MDC.get("X-B3-TraceId"));
        testFeign.test();
        return "test1";
    }

    @GetMapping("test2")
    public String test2() {
        log.info("controller test2 执行 {}", MDC.get("X-B3-TraceId"));
        restTemplate.getForEntity("http://127.0.0.1:8082/test", String.class);
        return "test2";
    }

}
