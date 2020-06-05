package com.purgerteam.log.trace.starter.util;

import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.Map;

/**
 * 配置 util
 *
 * @author <a href="mailto:yaoonlyi@gmail.com">purgeyao</a>
 * @since 1.0.0
 */
public class EnvironmentUtils {

    public static void addOrReplace(MutablePropertySources propertySources, Map<String, Object> map, String propertySourceName) {
        MapPropertySource target = null;
        if (propertySources.contains(propertySourceName)) {
            PropertySource<?> source = propertySources.get(propertySourceName);
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
            target = new MapPropertySource(propertySourceName, map);
        }
        if (!propertySources.contains(propertySourceName)) {
            propertySources.addLast(target);
        }
    }
}
