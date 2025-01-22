package com.wuzeyu.gateway.sdk.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author wuzeyu
 * @description 应用配置
 * @github github.com/kkkkendei
 */
@ConfigurationProperties("api-gateway-sdk")
public class GatewaySDKServiceProperties {

    /** 网关注册中心地址 */
    private String address;

    /** 系统标识 */
    private String systemId;

    /** 系统名称 */
    private String systemName;

    /** RPC注册中心；zookeeper://127.0.0.1:2181*/
    private String systemRegistry;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getSystemRegistry() {
        return systemRegistry;
    }

    public void setSystemRegistry(String systemRegistry) {
        this.systemRegistry = systemRegistry;
    }

}
