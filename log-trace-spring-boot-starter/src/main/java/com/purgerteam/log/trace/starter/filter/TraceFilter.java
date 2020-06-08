package com.purgerteam.log.trace.starter.filter;

import com.purgerteam.log.trace.starter.TraceContentFactory;
import com.purgerteam.log.trace.starter.TraceLogProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 请求拦截器 初始化 Trace 内容
 *
 * @author <a href="mailto:yaoonlyi@gmail.com">purgeyao</a>
 * @since 1.0.0
 */
public class TraceFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(TraceFilter.class);

    private TraceLogProperties traceLogProperties;

    public TraceFilter(TraceLogProperties traceLogProperties) {
        this.traceLogProperties = traceLogProperties;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = ((HttpServletRequest) servletRequest);

        Map<String, String> formatMap = new HashMap<>(16);

        // 获取自定义参数
        Set<String> expandFormat = traceLogProperties.getFormat();
        for (String k : expandFormat) {
            String v = request.getHeader(k);
            if (!StringUtils.isEmpty(v)) {
                formatMap.put(k, URLDecoder.decode(v, "UTF-8"));
            }
        }

        // 写入 MDC
        TraceContentFactory.storageMDC(formatMap);

        filterChain.doFilter(servletRequest, servletResponse);
        MDC.clear();
    }

}
