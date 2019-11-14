package com.purgerteam.log.trace.starter.instrument;

import com.purgerteam.log.trace.starter.Constants;
import org.slf4j.MDC;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import java.util.Map;

/**
 * @author purgeyao
 * @since 1.0
 */
public class TraceContentFactory implements EnvironmentAware {

    private Environment environment;

    public Map<String, String> assemblyTraceContent() {
        Map<String, String> copyOfContextMap = MDC.getCopyOfContextMap();
        String serviceName = environment.getProperty("spring.application.name");
        copyOfContextMap.put(Constants.LEGACY_PARENT_SERVICE_NAME, serviceName);
        return copyOfContextMap;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
