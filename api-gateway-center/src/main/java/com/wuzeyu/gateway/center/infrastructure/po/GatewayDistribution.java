package com.wuzeyu.gateway.center.infrastructure.po;


import java.util.Date;

/**
 * @author wuzeyu
 * @description 网关分配
 * @github github.com/kkkkendei
 */
public class GatewayDistribution {

    /** 自增主键 */
    private Integer id;
    /** 分组标识 */
    private String groupId;
    /** 网关标识 */
    private String gatewayId;
    /** 系统标识 */
    private String systemId;
    /** 系统名称 */
    private String systemName;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
