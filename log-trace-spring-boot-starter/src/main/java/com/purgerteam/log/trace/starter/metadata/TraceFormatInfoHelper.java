package com.purgerteam.log.trace.starter.metadata;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Trace log 格式 辅助类
 *
 * @author <a href="mailto:yaoonlyi@gmail.com">purgeyao</a>
 * @since 1.0.0
 */
public class TraceFormatInfoHelper {

    /**
     * 储存日志格式类表信息
     */
    private static final Set<TraceFormatInfo> TRACE_FORMAT_INFO_CACHE = new CopyOnWriteArraySet<>();

    /**
     * 添加日志信息
     * @param formatInfo 日志格式对象
     */
    public static void addTraceFormatInfo(TraceFormatInfo formatInfo) {
        TRACE_FORMAT_INFO_CACHE.add(formatInfo);
    }

    public static Set<TraceFormatInfo> getTraceFormatInfos() {
        return TRACE_FORMAT_INFO_CACHE;
    }
}
