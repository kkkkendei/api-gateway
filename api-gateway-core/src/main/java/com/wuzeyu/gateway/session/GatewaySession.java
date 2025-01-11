package com.wuzeyu.gateway.session;

import com.wuzeyu.gateway.bind.IGenericReference;

import java.util.Map;

/**
 * @author wuzeyu
 * @description 用户处理网关HTTP请求
 * @github github.com/kkkkendei
 */
public interface GatewaySession {

    Object get(String methodName, Map<String, Object> params);

    Object post(String methodName, Map<String, Object> params);

    IGenericReference getMapper();

    Configuration getConfiguration();

}
