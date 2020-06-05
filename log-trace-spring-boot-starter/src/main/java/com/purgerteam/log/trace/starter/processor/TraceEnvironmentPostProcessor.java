package com.purgerteam.log.trace.starter.processor;

import com.purgerteam.log.trace.starter.util.EnvironmentUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 日志格式处理器
 *
 * @author <a href="mailto:yaoonlyi@gmail.com">purgeyao</a>
 * @since 1.0.0
 */
public class TraceEnvironmentPostProcessor implements EnvironmentPostProcessor {

    private static final String PROPERTY_SOURCE_NAME = "defaultProperties";
    private static final String LEVEL_STR_ORIGINAL = "%5p [${spring.application.name:-},%X{X-B3-TraceId:-},%X{X-B3-SpanId:-},%X{X-Span-Export:-}]";
    private static final String LEVEL_STR_PARENT = "%5p [${spring.application.name:-},%X{X-B3-TraceId:-},%X{X-B3-ParentName:-},%X{X-B3-SpanId:-},%X{X-Span-Export:-}]";

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        Map<String, Object> map = new HashMap<>(1);
        map.put("logging.pattern.level", assemblyLevel(environment));
        EnvironmentUtils.addOrReplace(environment.getPropertySources(), map, PROPERTY_SOURCE_NAME);
    }

    /**
     * 处理输出格式 logging.pattern.level
     *
     * @param environment env对象
     * @return 日志格式
     */
    private String assemblyLevel(Environment environment) {
        // 获取自定义格式
        String format = environment.getProperty("spring.trace.log.format");
        // 判断是否为空 返回默认格式
        if (StringUtils.isEmpty(format)) {
            return LEVEL_STR_PARENT;
        }
        List<String> result = Arrays.asList(format.split(","));
        if (result.isEmpty()) {
            return LEVEL_STR_PARENT;
        }
        StringBuilder sb = new StringBuilder("%5p [");
        for (String value : result) {
            sb.append("%X{").append(value).append(":-}").append(",");
        }
        sb.deleteCharAt(sb.length() - 1).append("]");
        return sb.toString();
    }
}
