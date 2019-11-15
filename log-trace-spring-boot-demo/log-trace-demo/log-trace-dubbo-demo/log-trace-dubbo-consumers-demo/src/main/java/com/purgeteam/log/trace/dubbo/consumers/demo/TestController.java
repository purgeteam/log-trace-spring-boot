package com.purgeteam.log.trace.dubbo.consumers.demo;

import com.purgeteam.log.trace.dubbo.api.demo.EchoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.rpc.RpcContext;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author purgeyao
 * @since 1.0
 */
@Slf4j
@RestController
public class TestController {


    @Reference(version = "2.0.0")
    private EchoService echoService;

    @GetMapping("test1")
    public Object test() {
        log.info("controller test 执行 {}", MDC.get("X-B3-TraceId"));
        RpcContext.getContext().setAttachment("log-trace-dubbo-consumers-demo", "123");
        return echoService.echo("123");
    }

}
