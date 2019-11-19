package com.purgerteam.log.trace.starter.instrument.resttemplate;

import com.purgerteam.log.trace.starter.TraceContentFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * @author purgeyao
 * @since 1.0
 */
public class RestTemplatePostProcessor implements BeanPostProcessor, PriorityOrdered {

    private TraceContentFactory traceContentFactory;

    public RestTemplatePostProcessor(TraceContentFactory traceContentFactory) {
        this.traceContentFactory = traceContentFactory;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof RestTemplateBuilder) {
            RestTemplate restTemplate = ((RestTemplateBuilder) bean).build();
            processing(restTemplate);
        }
        if (bean instanceof RestTemplate) {
            RestTemplate restTemplate = (RestTemplate) bean;
            processing(restTemplate);
        }
        return bean;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1;
    }

    private void processing(RestTemplate restTemplate) {
        restTemplate.setInterceptors(Collections.singletonList(new TraceClientHttpRequestInterceptor(traceContentFactory)));
    }

}
