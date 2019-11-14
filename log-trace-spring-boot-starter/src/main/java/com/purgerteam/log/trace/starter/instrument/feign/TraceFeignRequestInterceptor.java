package com.purgerteam.log.trace.starter.instrument.feign;

import com.purgerteam.log.trace.starter.instrument.TraceContentFactory;
import feign.RequestInterceptor;
import feign.RequestTemplate;

import java.util.Map;

/**
 * @author purgeyao
 * @since 1.0
 */
public class TraceFeignRequestInterceptor implements RequestInterceptor {

    private TraceContentFactory traceContentFactory;

    public TraceFeignRequestInterceptor(TraceContentFactory traceContentFactory){
        this.traceContentFactory = traceContentFactory;
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        Map<String, String> copyOfContextMap = traceContentFactory.assemblyTraceContent();
        for (Map.Entry<String, String> copyOfContext : copyOfContextMap.entrySet()) {
            requestTemplate.header(copyOfContext.getKey(), copyOfContext.getValue());
        }
    }
}
