package com.purgerteam.log.trace.starter.instrument.feign;

import com.purgerteam.log.trace.starter.TraceContentFactory;
import feign.Client;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.cloud.openfeign.FeignContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author purgeyao
 * @since 1.0
 */
@Configuration
@ConditionalOnClass({Client.class, FeignContext.class, TraceContentFactory.class})
@AutoConfigureBefore(FeignAutoConfiguration.class)
public class TraceFeignClientAutoConfiguration {

    @Bean
    public TraceFeignRequestInterceptor basicAuthRequestInterceptor(TraceContentFactory traceContentFactory) {
        return new TraceFeignRequestInterceptor(traceContentFactory);
    }

}
