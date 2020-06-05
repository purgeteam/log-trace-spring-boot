package com.purgerteam.log.trace.starter.instrument.dubbo;

import com.purgerteam.log.trace.starter.util.EnvironmentUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.HashMap;
import java.util.Map;

/**
 * 加载 dubbo 相关配置
 *
 * @author purgeyao
 * @since 1.0
 */
public class TraceDubboEnvironmentPostProcessor implements EnvironmentPostProcessor {

    private static final String PROPERTY_SOURCE_NAME = "dubbo";

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("dubbo.consumer.filter", "TraceDubboConsumerFilter");
        map.put("dubbo.provider.filter", "TraceDubboProviderFilter");
        EnvironmentUtils.addOrReplace(environment.getPropertySources(), map, PROPERTY_SOURCE_NAME);
    }

}
