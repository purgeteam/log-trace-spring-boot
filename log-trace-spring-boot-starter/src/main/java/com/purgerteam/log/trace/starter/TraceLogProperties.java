package com.purgerteam.log.trace.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 链路追踪配置
 *
 * @author <a href="mailto:yaoonlyi@gmail.com">purgeyao</a>
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "spring.trace.log")
public class TraceLogProperties {

    /**
     * 日志格式顺序
     */
    private Set<String> format = new HashSet<>();

    /**
     * 日志格式核心参数集合 默认 [当前项目名称,链路请求id,父项目名称]
     */
    @Deprecated
    private Set<TraceFormatEnum> coreFormat = new HashSet<>(
            Arrays.asList(
                    TraceFormatEnum.LOCAL_NAME,
                    TraceFormatEnum.TRACE_ID,
                    TraceFormatEnum.PARENT_NAME
            )
    );

    /**
     * 日志格式自定义参数集合
     */
    @Deprecated
    private Set<String> expandFormat = new HashSet<>();


    public Set<String> getFormat() {
        return format;
    }

    public void setFormat(Set<String> format) {
        this.format = format;
    }

    public Set<TraceFormatEnum> getCoreFormat() {
        return coreFormat;
    }

    public void setCoreFormat(Set<TraceFormatEnum> coreFormat) {
        this.coreFormat = coreFormat;
    }

    public Set<String> getExpandFormat() {
        return expandFormat;
    }

    public void setExpandFormat(Set<String> expandFormat) {
        this.expandFormat = expandFormat;
    }
}
