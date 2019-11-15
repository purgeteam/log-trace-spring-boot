package com.purgeteam.log.trace.dubbo.consumers.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@Slf4j
@EnableFeignClients
@SpringBootApplication
public class TraceDubboConsumersSpringBootApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(TraceDubboConsumersSpringBootApplication.class, args);
        ConfigurableEnvironment environment = run.getEnvironment();
        log.info("启动");
    }

}
