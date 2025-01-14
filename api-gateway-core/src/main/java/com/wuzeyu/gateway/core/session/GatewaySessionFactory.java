package com.wuzeyu.gateway.core.session;

/**
 * @author wuzeyu
 * @description 网关会话工厂接口
 * @github github.com/kkkkendei
 */
public interface GatewaySessionFactory {

    GatewaySession openSession(String uri);

}
