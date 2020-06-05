package com.purgerteam.log.trace.starter.handlers;

import java.util.Map;

/**
 * 元对象字段填充控制器抽象类，实现自定义日志参数写入
 *
 * @author <a href="mailto:yaoonlyi@gmail.com">purgeyao</a>
 * @since 1.0.0
 */
public interface TraceMetaObjectHandler {

    /**
     * 额外参数填充
     */
    void additionalFill(Map<String, String> traceContentMap);

    /**
     * 根据 日志格式名称 填充 内容
     *
     * @param traceContent trace日志格式内容
     * @param fieldName    日志格式名称参数
     * @param fieldVal     内容
     */
    default void strictInsertFill(Map<String, String> traceContent, String fieldName, String fieldVal) {
        traceContent.put(fieldName, fieldVal);
    }
}
