package com.wuzeyu.gateway.test;

import com.wuzeyu.gateway.session.SessionServer;
import io.netty.channel.Channel;
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
        SessionServer server = new SessionServer();

        Future<Channel> future = Executors.newFixedThreadPool(2).submit(server);
        Channel channel = future.get();

        if (channel == null) throw new RuntimeException("netty server start error: channel is null");
        while (!channel.isActive()) {
            LOG.info("NettyServer启动服务。。。");
            Thread.sleep(500);
        }

        LOG.info("NettyServer启动服务完成 {}", channel.localAddress());
        Thread.sleep(Long.MAX_VALUE);
    }

}
