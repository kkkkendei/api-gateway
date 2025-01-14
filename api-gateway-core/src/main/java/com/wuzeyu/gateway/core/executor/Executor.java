package com.wuzeyu.gateway.core.executor;

import com.wuzeyu.gateway.core.executor.result.SessionResult;
import com.wuzeyu.gateway.core.mapping.HttpStatement;

import java.util.Map;

/**
 * @author wuzeyu
 * @description 执行器
 * @github github.com/kkkkendei
 */
public interface Executor {

    SessionResult exec(HttpStatement httpStatement, Map<String, Object> params) throws Exception;

}
