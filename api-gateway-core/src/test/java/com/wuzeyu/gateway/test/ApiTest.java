package com.wuzeyu.gateway.test;

import com.wuzeyu.gateway.session.Configuration;
import com.wuzeyu.gateway.session.GenericReferenceSessionFactoryBuilder;
import com.wuzeyu.gateway.session.SessionServer;
import io.netty.channel.Channel;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ApiTest {

    private final Logger LOG = LoggerFactory.getLogger(SessionServer.class);

    @Test
    public void test() throws ExecutionException, InterruptedException {

        Configuration configuration = new Configuration();
        configuration.addReference("api-gateway-test", "cn.bugstack.gateway.rpc.IActivityBooth", "sayHi");
        GenericReferenceSessionFactoryBuilder builder = new GenericReferenceSessionFactoryBuilder();
        Future<Channel> future = builder.build(configuration);

        LOG.info("NettyServer启动服务完成 {}", future.get().id());
        Thread.sleep(Long.MAX_VALUE);
    }

}
