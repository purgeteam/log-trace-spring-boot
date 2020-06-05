package com.purgeteam.log.trace.service.a.demo.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

/**
 * {@link RestTemplate}高并发下异常与配置说明 1、java.util.ConcurrentModificationException
 * 2、java.net.SocketTimeoutException Connection timed out
 *
 * @author purgeyao
 * @since 1.0
 */
@Configuration
public class RestTemplateConfig {

    @Lazy
    @Bean
    @Primary
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public String stringTer() {
        return "asdf";
    }
}
