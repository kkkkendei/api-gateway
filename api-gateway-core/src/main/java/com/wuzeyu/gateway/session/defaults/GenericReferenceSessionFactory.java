package com.wuzeyu.gateway.session.defaults;

import com.wuzeyu.gateway.session.Configuration;
import com.wuzeyu.gateway.session.IGenericReferenceSessionFactory;
import com.wuzeyu.gateway.session.SessionServer;
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
public class GenericReferenceSessionFactory implements IGenericReferenceSessionFactory {

    private final Logger LOG = LoggerFactory.getLogger(GenericReferenceSessionFactory.class);

    private final Configuration configuration;

    public GenericReferenceSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }


    @Override
    public Future<Channel> openSession() throws ExecutionException, InterruptedException {

        SessionServer server = new SessionServer(configuration);

        Future<Channel> future = Executors.newFixedThreadPool(2).submit(server);
        Channel channel = future.get();

        if (channel == null) throw new RuntimeException("netty server start error channel is null");

        while (!channel.isActive()) {
            LOG.info("netty server gateway start Ing ...");
            Thread.sleep(500);
        }
        LOG.info("netty server gateway start Done! {}", channel.localAddress());

        return future;
    }
}
