package com.purgeteam.log.trace.servcie.b.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@Slf4j
@SpringBootApplication
public class TraceServiceB15xSpringBootApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(TraceServiceB15xSpringBootApplication.class, args);
        ConfigurableEnvironment environment = run.getEnvironment();
        log.info("启动");
    }

}
