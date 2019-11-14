package com.purgerteam.log.trace.starter.processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.*;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author purgeyao
 * @since 1.0
 */
public class TraceEnvironmentPostProcessor implements EnvironmentPostProcessor {

    private static final String PROPERTY_SOURCE_NAME = "defaultProperties";
    private static final String LEVEL_STR_ORIGINAL = "%5p [${spring.application.name:-},%X{X-B3-TraceId:-},%X{X-B3-SpanId:-},%X{X-Span-Export:-}]";
    private static final String LEVEL_STR_PARENT = "%5p [${spring.application.name:-},%X{X-B3-TraceId:-},%X{X-B3-ParentName:-},%X{X-B3-SpanId:-},%X{X-Span-Export:-}]";

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        Map<String, Object> map = new HashMap<>(1);
        map.put("logging.pattern.level", assemblyLevel(environment));
        addOrReplace(environment.getPropertySources(), map);
    }

    private void addOrReplace(MutablePropertySources propertySources, Map<String, Object> map) {
        MapPropertySource target = null;
        if (propertySources.contains(PROPERTY_SOURCE_NAME)) {
            PropertySource<?> source = propertySources.get(PROPERTY_SOURCE_NAME);
            if (source instanceof MapPropertySource) {
                target = (MapPropertySource) source;
                for (String key : map.keySet()) {
                    if (!target.containsProperty(key)) {
                        target.getSource().put(key, map.get(key));
                    }
                }
            }
        }
        if (target == null) {
            target = new MapPropertySource(PROPERTY_SOURCE_NAME, map);
        }
        if (!propertySources.contains(PROPERTY_SOURCE_NAME)) {
            propertySources.addLast(target);
        }
    }

    private String assemblyLevel(Environment environment) {
        String format = environment.getProperty("spring.trace.log.format");
        if (StringUtils.isEmpty(format)) {
            return LEVEL_STR_PARENT;
        }
        List<String> result = Arrays.asList(format.split(","));
        if (result.isEmpty()){
            return LEVEL_STR_PARENT;
        }
        StringBuffer sb = new StringBuffer("%5p [");
        for (String value : result) {
            sb.append("%X{").append(value).append(":-}").append(",");
        }
        sb.deleteCharAt(sb.length() - 1).append("]");
        return sb.toString();
    }
}
