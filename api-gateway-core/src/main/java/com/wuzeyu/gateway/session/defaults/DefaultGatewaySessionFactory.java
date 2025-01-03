package com.wuzeyu.gateway.session.defaults;

import com.wuzeyu.gateway.session.Configuration;
import com.wuzeyu.gateway.session.GatewaySession;
import com.wuzeyu.gateway.session.GatewaySessionFactory;
import com.wuzeyu.gateway.socket.GatewaySocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.netty.channel.Channel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author wuzeyu
 * @description 泛化调用会话工厂
 * @github github.com/kkkkendei
 */
public class DefaultGatewaySessionFactory implements GatewaySessionFactory {

    //private final Logger LOG = LoggerFactory.getLogger(DefaultGatewaySessionFactory.class);

    private final Configuration configuration;

    public DefaultGatewaySessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public GatewaySession openSession() throws ExecutionException, InterruptedException {
        return new DefaultGatewaySession(configuration);
    }

   /* @Override
    public Future<Channel> openSession() throws ExecutionException, InterruptedException {

        GatewaySocketServer server = new GatewaySocketServer(configuration);

        Future<Channel> future = Executors.newFixedThreadPool(2).submit(server);
        Channel channel = future.get();

        if (channel == null) throw new RuntimeException("netty server start error channel is null");

        while (!channel.isActive()) {
            LOG.info("netty server gateway start Ing ...");
            Thread.sleep(500);
        }
        LOG.info("netty server gateway start Done! {}", channel.localAddress());

        return future;
    }*/
}
