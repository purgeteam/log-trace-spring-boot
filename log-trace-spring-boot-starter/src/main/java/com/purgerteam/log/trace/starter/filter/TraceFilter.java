package com.purgerteam.log.trace.starter.filter;

import com.purgerteam.log.trace.starter.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

/**
 * @author purgeyao
 * @since 1.0
 */
public class TraceFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(TraceFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = ((HttpServletRequest) servletRequest);
        // "traceId"
        String headerTraceId = request.getHeader(Constants.LEGACY_TRACE_ID_NAME);
        log.debug("TraceFilter headerTraceId {}", headerTraceId);
        // 如果为空，则表示第一次访问，即上游服务端的请求
        if (StringUtils.isEmpty(headerTraceId)) {
            MDC.put(Constants.LEGACY_TRACE_ID_NAME, traceIdString());
        } else {
            MDC.put(Constants.LEGACY_TRACE_ID_NAME, headerTraceId);
        }

        // "ParentName"
        String headerParentName = request.getHeader(Constants.LEGACY_PARENT_SERVICE_NAME);
        if (StringUtils.isEmpty(headerTraceId)) {
            MDC.put(Constants.LEGACY_PARENT_SERVICE_NAME, "this");
        } else {
            MDC.put(Constants.LEGACY_PARENT_SERVICE_NAME, headerParentName);
        }

        filterChain.doFilter(servletRequest, servletResponse);
        MDC.clear();
    }


    private String traceIdString() {
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
