package com.purgerteam.log.trace.starter.instrument.dubbo.provider;

import com.purgerteam.log.trace.starter.TraceContentFactory;
import org.apache.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * dubbo Provider 处理
 *
 * @author purgeyao
 * @since 1.0
 */
public class TraceDubboProviderFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(TraceDubboProviderFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        Map<String, String> attachments = invocation.getAttachments();
        TraceContentFactory.storageMDC(attachments);
        Result result = invoker.invoke(invocation);

        // after filter ...
        log.debug("Trace dubbo provider copyOfContextMap {}", attachments.toString());
        return result;
    }
}
