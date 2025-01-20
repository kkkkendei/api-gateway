package com.wuzeyu.gateway.core.session;

import com.wuzeyu.gateway.core.bind.IGenericReference;

import java.util.Map;

/**
 * @author wuzeyu
 * @description 用户处理网关 HTTP 请求
 * @github github.com/kkkkendei
 */
public interface GatewaySession {

    Object get(String methodName, Map<String, Object> params);

    Object post(String methodName, Map<String, Object> params);

    IGenericReference getMapper();

    Configuration getConfiguration();

}
