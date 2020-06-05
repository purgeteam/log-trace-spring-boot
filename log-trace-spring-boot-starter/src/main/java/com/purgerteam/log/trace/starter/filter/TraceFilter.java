package com.purgerteam.log.trace.starter.filter;

import com.purgerteam.log.trace.starter.Constants;
import com.purgerteam.log.trace.starter.TraceContentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求拦截器 初始化 Trace 内容
 *
 * @author <a href="mailto:yaoonlyi@gmail.com">purgeyao</a>
 * @since 1.0.0
 */
public class TraceFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(TraceFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = ((HttpServletRequest) servletRequest);

        // TODO: 2020/6/5 换为动态获取

        // "traceId"
        String headerTraceId = request.getHeader(Constants.LEGACY_TRACE_ID_NAME);
        // "ParentName"
        String headerParentName = request.getHeader(Constants.LEGACY_PARENT_SERVICE_NAME);

        // 写入 MDC
        Map<String, String> map = new HashMap<>(16);
        map.put(Constants.LEGACY_TRACE_ID_NAME, headerTraceId);
        map.put(Constants.LEGACY_PARENT_SERVICE_NAME, headerParentName);
        TraceContentFactory.storageMDC(map);

        filterChain.doFilter(servletRequest, servletResponse);
        MDC.clear();
    }

}
