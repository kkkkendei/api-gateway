package com.wuzeyu.gateway.test;

import com.wuzeyu.gateway.mapping.HttpCommandType;
import com.wuzeyu.gateway.mapping.HttpStatement;
import com.wuzeyu.gateway.session.Configuration;
import com.wuzeyu.gateway.session.GatewaySessionFactory;
import com.wuzeyu.gateway.session.defaults.DefaultGatewaySessionFactory;
import com.wuzeyu.gateway.socket.GatewaySocketServer;
import io.netty.channel.Channel;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class ApiTest {

    private final Logger LOG = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void test() throws ExecutionException, InterruptedException {

        //创建配置信息加载注册
        Configuration configuration = new Configuration();

        HttpStatement httpStatement01 = new HttpStatement(
                "api-gateway-test",
                "cn.bugstack.gateway.rpc.IActivityBooth",
                "java.lang.String",
                "sayHi",
                "/wg/activity/sayHi",
                HttpCommandType.GET,
                false
        );

        HttpStatement httpStatement02 = new HttpStatement(
                "api-gateway-test",
                "cn.bugstack.gateway.rpc.IActivityBooth",
                "cn.bugstack.gateway.rpc.dto.XReq",
                "insert",
                "/wg/activity/insert",
                HttpCommandType.POST,
                true
        );

        configuration.addMapper(httpStatement01);
        configuration.addMapper(httpStatement02);

        //基于配置构建会话工厂
        GatewaySessionFactory gatewaySessionFactory = new DefaultGatewaySessionFactory(configuration);
        //创建启动网关网络服务
        GatewaySocketServer server = new GatewaySocketServer(configuration, gatewaySessionFactory);

        Future<Channel> future = Executors.newFixedThreadPool(2).submit(server);
        Channel channel = future.get();

        if (channel == null) throw new RuntimeException("netty server start error, channel is null");

        while (!channel.isActive()) {
            LOG.info("netty server gateway start Ing ...");
            Thread.sleep(500);
        }

        LOG.info("NettyServer启动服务完成 {}", future.get().localAddress());
        Thread.sleep(Long.MAX_VALUE);

    }

}
