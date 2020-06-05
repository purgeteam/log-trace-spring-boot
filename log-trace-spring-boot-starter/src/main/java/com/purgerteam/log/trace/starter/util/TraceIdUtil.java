package com.purgerteam.log.trace.starter.util;

import java.util.UUID;

/**
 * traceId 处理
 *
 * @author <a href="mailto:yaoonlyi@gmail.com">purgeyao</a>
 * @since 1.0.0
 */
public class TraceIdUtil {

    /**
     * 生产 traceId
     *
     * @return traceId
     */
    public static String traceIdString() {
        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString().replace("-", "");
        return getUUID(uuidStr, 16);
    }

    /**
     * 处理 traceId 长度
     *
     * @param uuid 原始uuid
     * @param len  长度
     * @return traceId
     */
    public static String getUUID(String uuid, int len) {
        if (0 >= len) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(uuid.charAt(i));
        }
        return sb.toString();
    }
}
