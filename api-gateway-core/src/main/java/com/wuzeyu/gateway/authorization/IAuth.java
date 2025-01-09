package com.wuzeyu.gateway.authorization;


/**
 * @author wuzeyu
 * @description 认证服务接口
 * @github github.com/kkkkendei
 */
public interface IAuth {

    boolean validate(String id, String token);

}
