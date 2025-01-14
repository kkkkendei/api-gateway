package com.wuzeyu.gateway.core.authorization;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author wuzeyu
 * @description 验证Token
 * @github github.com/kkkkendei
 */
public class GatewayAuthorizingToken implements AuthenticationToken {

    private static final long serialVersionUID = 1L;

    // 通信管道ID
    private String uId;

    // JSON WEB TOKEN
    private String jwt;

    public GatewayAuthorizingToken() {}

    public GatewayAuthorizingToken(String uId, String jwt) {
        this.uId = uId;
        this.jwt = jwt;
    }

    @Override
    public Object getPrincipal() {
        return uId;
    }

    @Override
    public Object getCredentials() {
        return this.jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
