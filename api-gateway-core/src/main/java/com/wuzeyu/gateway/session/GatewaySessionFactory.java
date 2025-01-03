package com.wuzeyu.gateway.session;

import io.netty.channel.Channel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author wuzeyu
 * @description 网关会话工厂接口
 * @github github.com/kkkkendei
 */
public interface GatewaySessionFactory {

    GatewaySession openSession() throws ExecutionException, InterruptedException;

}
