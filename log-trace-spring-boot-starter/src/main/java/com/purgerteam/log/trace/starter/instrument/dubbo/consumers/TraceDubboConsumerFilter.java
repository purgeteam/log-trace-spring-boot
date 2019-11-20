package com.purgerteam.log.trace.starter.instrument.dubbo.consumers;

import com.purgerteam.log.trace.starter.TraceContentFactory;
import org.apache.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * dubbo Consumer 处理
 *
 * @author purgeyao
 * @since 1.0
 */
public class TraceDubboConsumerFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(TraceDubboConsumerFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Map<String, String> copyOfContextMap = TraceContentFactory.assemblyTraceContentStatic();
        // before filter ...
        for (Map.Entry<String, String> copyOfContext : copyOfContextMap.entrySet()) {
            invocation.setAttachment(copyOfContext.getKey(), copyOfContext.getValue());
            RpcContext.getContext().setAttachment(copyOfContext.getKey(), copyOfContext.getValue());
        }
        Result result = invoker.invoke(invocation);

        // after filter ...
        log.debug("Trace dubbo Consumer copyOfContextMap {}", copyOfContextMap.toString());
        return result;
    }
}
