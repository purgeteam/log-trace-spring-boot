package com.purgeteam.log.trace.service.a.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author purgeyao
 * @since 1.0
 */
@FeignClient(name = "log-trace-service-b-demo", url = "127.0.0.1:8082")
public interface TestFeign {

    @GetMapping("/test")
    String test();

}
