package com.wuzeyu.gateway.center.domain.manage.model.vo;


/**
 * @author wuzeyu
 * @description 网关服务配置
 * @github github.com/kkkkendei
 */
public class GatewayServerVO {

    /** 分组标识 */
    private String groupId;
    /** 分组名称 */
    private String groupName;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

}
