package com.purgeteam.log.trace.dubbo.provider.demo;

import com.purgeteam.log.trace.dubbo.api.demo.EchoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;

/**
 * @author purgeyao
 * @since 1.0
 */
@Slf4j
@Service(version = "2.0.0")
public class EchoServiceImpl implements EchoService {

    @Override
    public String echo(String message) {
        log.info("dubbo message={}", message);
        Object[] arguments = RpcContext.getContext().getArguments();
        log.info("dubbo log-trace-dubbo-consumers-demo={}", arguments);
        return "[echo version = \"1.0.0\"] Hello, " + message;
    }
}
