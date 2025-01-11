package com.wuzeyu.gateway.bind;

import com.wuzeyu.gateway.executor.result.SessionResult;

import java.util.Map;

/**
 * @author wuzeyu
 * @description 代理泛化调用接口
 * @github github.com/kkkkendei
 */
public interface IGenericReference {

    //暂时只接受一个参数
    SessionResult $invoke(Map<String, Object> args);

}
