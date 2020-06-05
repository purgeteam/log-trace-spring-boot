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

    @GetMapping("testFeign")
    public String testFeign() {
        log.info("controller test1 执行 {}", MDC.get("X-B3-TraceId"));
        String test = testFeign.test();
        log.info("执行 testFeign 方法返回值{}", test);
        return "test1";
    }

    @GetMapping("restTemplateTest")
    public String restTemplateTest() {
        log.info("controller test2 执行 {}", MDC.get("X-B3-TraceId"));
        String body = restTemplate.getForEntity("http://127.0.0.1:8082/test", String.class).getBody();
        log.info("执行 restTemplate 方法返回值{}", body);
        return "test2";
    }

}
