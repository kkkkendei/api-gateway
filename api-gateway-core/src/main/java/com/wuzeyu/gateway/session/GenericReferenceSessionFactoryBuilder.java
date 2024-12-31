package com.wuzeyu.gateway.session;

import com.wuzeyu.gateway.session.defaults.DefaultGatewaySessionFactory;
import io.netty.channel.Channel;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author wuzeyu
 * @description 会话工厂建造类
 * @github github.com/kkkkendei
 */
public class GenericReferenceSessionFactoryBuilder {

    public Future<Channel> build(Configuration configuration) {
        GatewaySessionFactory genericReferenceSessionFactory = new DefaultGatewaySessionFactory(configuration);
        try {
            return genericReferenceSessionFactory.openSession();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
