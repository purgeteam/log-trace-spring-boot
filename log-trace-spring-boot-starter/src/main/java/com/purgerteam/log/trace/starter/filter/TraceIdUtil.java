package com.purgerteam.log.trace.starter.filter;

import java.util.UUID;

/**
 * @author purgeyao
 * @since 1.0
 */
public class TraceIdUtil {

    public static String traceIdString() {
        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString().replace("-", "");
        return getUUID(uuidStr, 16);
    }

    private static String getUUID(String uuid, int len) {
        if (0 >= len) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i++) {
            sb.append(uuid.charAt(i));
        }
        return sb.toString();
    }
}
