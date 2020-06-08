package com.purgerteam.log.trace.starter;

import com.purgerteam.log.trace.starter.handlers.TraceMetaObjectHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求链路内容工厂
 *
 * @author <a href="mailto:yaoonlyi@gmail.com">purgeyao</a>
 * @since 1.0.0
 */
public class TraceContentFactory implements EnvironmentAware {

    private static final Logger log = LoggerFactory.getLogger(TraceContentFactory.class);

    private static Environment environment;

    private static Map<String, TraceMetaObjectHandler> traceMetaObjectHandlerMap;

    public TraceContentFactory(Map<String, TraceMetaObjectHandler> traceMetaObjectHandlerMap) {
        TraceContentFactory.traceMetaObjectHandlerMap = traceMetaObjectHandlerMap;
    }

    /**
     * 储存本地 MDC
     *
     * @param traceContentMap 内容集合
     */
    public static void storageMDC(Map<String, String> traceContentMap) {
        // 执行 TraceMetaObjectHandler 拓展器
        for (Map.Entry<String, TraceMetaObjectHandler> entry : traceMetaObjectHandlerMap.entrySet()) {
            TraceMetaObjectHandler handler = entry.getValue();
            handler.additionalFill(traceContentMap);
        }

        // 写入 MDC
        for (Map.Entry<String, String> entry : traceContentMap.entrySet()) {
            MDC.put(entry.getKey(), entry.getValue());
        }

        log.debug("[TraceContentFactory] 请求流量: traceId={}, ParentName={}",
                MDC.get(Constants.LEGACY_TRACE_ID_NAME), MDC.get(Constants.LEGACY_PARENT_SERVICE_NAME));
    }

    public Map<String, String> assemblyTraceContent() {
        return buildTraceContent();
    }

    public static Map<String, String> assemblyTraceContentStatic() {
        return buildTraceContent();
    }

    /**
     * 获取 MDC 内容 同时添加 X-B3-ParentName 参数
     *
     * @return MDC map
     */
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
