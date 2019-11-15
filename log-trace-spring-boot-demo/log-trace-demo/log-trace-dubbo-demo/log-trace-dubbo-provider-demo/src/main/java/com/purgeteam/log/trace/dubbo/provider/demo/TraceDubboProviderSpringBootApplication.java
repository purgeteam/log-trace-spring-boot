package com.purgeteam.log.trace.dubbo.provider.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@Slf4j
@EnableDiscoveryClient
@SpringBootApplication
public class TraceDubboProviderSpringBootApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(TraceDubboProviderSpringBootApplication.class, args);
        ConfigurableEnvironment environment = run.getEnvironment();
        log.info("启动");
    }

}
