package com.wuzeyu.gateway.center.domain.register.model.vo;


/**
 * @author wuzeyu
 * @descripiton 应用服务 VO
 * @github github.com/kkkkendei
 */
public class ApplicationSystemVO {

    /** 系统标识 */
    private String systemId;
    /** 系统名称 */
    private String systemName;
    /** 系统类型；RPC、HTTP*/
    private String systemType;
    /** 注册中心；zookeeper://127.0.0.1:2181*/
    private String systemRegistry;

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

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    public String getSystemRegistry() {
        return systemRegistry;
    }

    public void setSystemRegistry(String systemRegistry) {
        this.systemRegistry = systemRegistry;
    }

}
