package com.wuzeyu.gateway.executor;

import com.wuzeyu.gateway.executor.result.GatewayResult;
import com.wuzeyu.gateway.mapping.HttpStatement;

import java.util.Map;

/**
 * @author wuzeyu
 * @description 执行器
 * @github github.com/kkkkendei
 */
public interface Executor {

    GatewayResult exec(HttpStatement httpStatement, Map<String, Object> params) throws Exception;

}
