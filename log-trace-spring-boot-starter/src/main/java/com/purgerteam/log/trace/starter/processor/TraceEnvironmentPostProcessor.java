package com.purgerteam.log.trace.starter.processor;

import com.purgerteam.log.trace.starter.Constants;
import com.purgerteam.log.trace.starter.TraceFormatEnum;
import com.purgerteam.log.trace.starter.util.EnvironmentUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 日志格式处理器
 *
 * @author <a href="mailto:yaoonlyi@gmail.com">purgeyao</a>
 * @since 1.0.0
 */
public class TraceEnvironmentPostProcessor implements EnvironmentPostProcessor {

    private static final String PROPERTY_SOURCE_NAME = "defaultProperties";
    private static final String LEVEL_STR_ORIGINAL = "%5p [${spring.application.name:-},%X{X-B3-TraceId:-},%X{X-B3-SpanId:-},%X{X-Span-Export:-}]";
    private static final String LEVEL_STR_PARENT = "%5p [${spring.application.name:-},%X{X-B3-TraceId:-},%X{X-B3-ParentName:-}]";

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        List<String> envList = getFormatEnv(environment);
        Map<String, Object> map = new HashMap<>(1);
        map.put("logging.pattern.level", assemblyLevel(envList));
        EnvironmentUtils.addOrReplace(environment.getPropertySources(), map, PROPERTY_SOURCE_NAME);
    }

    /**
     * 获取 spring.trace.log.format list 对象
     *
     * @param environment env对象
     * @return
     */
    private List<String> getFormatEnv(Environment environment) {
        List<String> result = new ArrayList<>();
        // 获取自定义格式
        String format = environment.getProperty("spring.trace.log.format");
        // 判断是否为空 返回默认格式
        if (StringUtils.isEmpty(format)) {
            result.add(TraceFormatEnum.LOCAL_NAME.getValue());
            result.add(TraceFormatEnum.TRACE_ID.getValue());
            result.add(TraceFormatEnum.PARENT_NAME.getValue());
        } else {
            result.addAll(Arrays.asList(format.split(",")));
        }
        return result;
    }

    /**
     * 处理输出格式 logging.pattern.level
     *
     * @param result 格式集合
     * @return 日志格式
     */
    private String assemblyLevel(List<String> result) {
        // 判断是否为空 返回默认格式
        if (result.isEmpty()) {
            return LEVEL_STR_PARENT;
        }
        // 拼接 格式字符串
        StringBuilder sb = new StringBuilder("%5p [");
        for (String value : result) {
            if (Constants.LOCAL_NAME.equals(value)) {
                sb.append("${").append(value).append(":-}").append(",");
            } else if (TraceFormatEnum.LOCAL_NAME.name().equals(value)) {
                sb.append("${").append(Constants.LOCAL_NAME).append(":-}").append(",");
            } else {
                sb.append("%X{").append(value).append(":-}").append(",");
            }
        }
        sb.deleteCharAt(sb.length() - 1).append("]");
        return sb.toString();
    }

}
