package com.purgeteam.log.trace.service.a.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@Slf4j
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class TraceServiceA15xSpringBootApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(TraceServiceA15xSpringBootApplication.class, args);
        ConfigurableEnvironment environment = run.getEnvironment();
        log.info("启动");
    }

}
