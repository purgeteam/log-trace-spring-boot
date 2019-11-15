package com.purgeteam.log.trace.servcie.b.demo;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
    public String test(HttpServletRequest request) {

        String traceId1 = request.getHeader("X-B3-TraceId");
        log.info("header traceId {}", traceId1);

        //        MDC.put("X-B3-TraceId", String.valueOf(Math.random()));
        log.info("controller test 执行 {}", MDC.get("X-B3-TraceId"));
        testService.test();
        return "test";
    }
}
