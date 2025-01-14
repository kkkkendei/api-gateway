package com.wuzeyu.gateway.core.datasource;

/**
 * @author wuzeyu
 * @description 连接接口
 * @github github.com/kkkkendei
 */
public interface Connection {

    Object execute(String method, String[] parameterTypes, String[] parameterNames, Object[] args);

}
