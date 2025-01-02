package com.wuzeyu.gateway.session;

import com.wuzeyu.gateway.bind.IGenericReference;

/**
 * @author wuzeyu
 * @description 用户处理网关HTTP请求
 * @github github.com/kkkkendei
 */
public interface GatewaySession {

    Object get(String uri, Object parameter);

    IGenericReference getMapper(String uri);

    Configuration getConfiguration();

}
