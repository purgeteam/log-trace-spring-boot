package com.purgeteam.log.trace.dubbo.provider.demo;

import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.service.EchoService;

/**
 * @author purgeyao
 * @since 1.0
 */
@Service(version = "1.0.0")
public class EchoServiceImpl implements EchoService {

    @Override
    public Object $echo(Object message) {
        return "[echo version = \"1.0.0\"] Hello, " + message;
    }
}
