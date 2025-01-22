package com.wuzeyu.gateway.sdk;

/**
 * @author wuzeyu
 * @description 网关异常
 * @github github.com/kkkkendei
 */
public class GatewayException extends RuntimeException {

    public GatewayException(String msg) {
        super(msg);
    }

    public GatewayException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
