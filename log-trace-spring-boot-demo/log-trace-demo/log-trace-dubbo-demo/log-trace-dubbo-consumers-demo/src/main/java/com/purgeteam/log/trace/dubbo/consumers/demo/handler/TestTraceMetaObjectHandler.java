package com.purgeteam.log.trace.dubbo.consumers.demo.handler;

import com.purgerteam.log.trace.starter.handlers.TraceMetaObjectHandler;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 填充器测试
 *
 * @author <a href="mailto:yaoonlyi@gmail.com">purgeyao</a>
 * @since 1.0.0
 */
@Component
public class TestTraceMetaObjectHandler implements TraceMetaObjectHandler {

    @Override
    public void additionalFill(Map<String, String> traceContentMap) {
        // 填充
        this.strictInsertFill(traceContentMap, "userCode", UserUtil.getUserName().getCode());
        this.strictInsertFill(traceContentMap, "userName", UserUtil.getUserName().getName());
    }
}
