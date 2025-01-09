package com.wuzeyu.gateway.authorization;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author wuzeyu
 * @description 验证Token
 * @github github.com/kkkkendei
 */
public class GatewayAuthorizingToken implements AuthenticationToken {

    private static final long serialVersionUID = 1L;

    // 通信管道ID
    private String channelId;

    // JSON WEB TOKEN
    private String jwt;

    public GatewayAuthorizingToken() {}

    public GatewayAuthorizingToken(String channelId, String jwt) {
        this.channelId = channelId;
        this.jwt = jwt;
    }

    @Override
    public Object getPrincipal() {
        return channelId;
    }

    @Override
    public Object getCredentials() {
        return this.jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
