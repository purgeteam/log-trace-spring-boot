package com.purgerteam.log.trace.starter.handlers;

import com.purgerteam.log.trace.starter.Constants;
import com.purgerteam.log.trace.starter.util.TraceIdUtil;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 默认日志格式处理器
 *
 * @author <a href="mailto:yaoonlyi@gmail.com">purgeyao</a>
 * @since 1.0.0
 */
public class DefaultTraceMetaObjectHandler implements TraceMetaObjectHandler {

    @Override
    public void additionalFill(Map<String, String> traceContentMap) {
        String headerTraceId = traceContentMap.get(Constants.LEGACY_TRACE_ID_NAME);
        // 如果为空，则表示第一次访问，即上游服务端的请求
        if (StringUtils.isEmpty(headerTraceId)) {
            this.strictInsertFill(traceContentMap, Constants.LEGACY_TRACE_ID_NAME, TraceIdUtil.traceIdString());
        } else {
            this.strictInsertFill(traceContentMap, Constants.LEGACY_TRACE_ID_NAME, headerTraceId);
        }

        // "ParentName" 如果为空设置为 this
        String headerParentName = traceContentMap.get(Constants.LEGACY_PARENT_SERVICE_NAME);
        if (StringUtils.isEmpty(headerTraceId)) {
            this.strictInsertFill(traceContentMap, Constants.LEGACY_PARENT_SERVICE_NAME, "this");
        } else {
            this.strictInsertFill(traceContentMap, Constants.LEGACY_PARENT_SERVICE_NAME, headerParentName);
        }
    }
}
