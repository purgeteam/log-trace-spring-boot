package com.purgerteam.log.trace.starter.instrument;

import com.purgerteam.log.trace.starter.Constants;
import com.purgerteam.log.trace.starter.filter.TraceIdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author purgeyao
 * @since 1.0
 */
public class TraceContentFactory implements EnvironmentAware {

    private static final Logger log = LoggerFactory.getLogger(TraceContentFactory.class);

    private static Environment environment;

    /**
     * 储存本地MDC
     *
     * @param traceContentMap 内容集合
     */
    public static void storageMDC(Map<String, String> traceContentMap) {
        String headerTraceId = traceContentMap.get(Constants.LEGACY_TRACE_ID_NAME);
        log.debug("Trace traceId {}", headerTraceId);
        // 如果为空，则表示第一次访问，即上游服务端的请求
        if (StringUtils.isEmpty(headerTraceId)) {
            MDC.put(Constants.LEGACY_TRACE_ID_NAME, TraceIdUtil.traceIdString());
        } else {
            MDC.put(Constants.LEGACY_TRACE_ID_NAME, headerTraceId);
        }

        // "ParentName"
        String headerParentName = traceContentMap.get(Constants.LEGACY_PARENT_SERVICE_NAME);
        if (StringUtils.isEmpty(headerTraceId)) {
            MDC.put(Constants.LEGACY_PARENT_SERVICE_NAME, "this");
        } else {
            MDC.put(Constants.LEGACY_PARENT_SERVICE_NAME, headerParentName);
        }
    }

    public Map<String, String> assemblyTraceContent() {
        return buildTraceContent();
    }

    public static Map<String, String> assemblyTraceContentStatic() {
        return buildTraceContent();
    }

    private static Map<String, String> buildTraceContent() {
        Map<String, String> traceContentMap = MDC.getCopyOfContextMap();
        if (traceContentMap == null) {
            traceContentMap = new HashMap<>(16);
        }
        String serviceName = environment.getProperty("spring.application.name");
        traceContentMap.put(Constants.LEGACY_PARENT_SERVICE_NAME, serviceName);
        return traceContentMap;
    }

    @Override
    public void setEnvironment(Environment environment) {
        TraceContentFactory.environment = environment;
    }
}
