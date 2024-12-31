package com.wuzeyu.gateway.session;

import io.netty.channel.Channel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author wuzeyu
 * @description 泛化调用会话工厂接口
 * @github github.com/kkkkendei
 */
public interface GatewaySessionFactory {

    Future<Channel> openSession() throws ExecutionException, InterruptedException;

}
