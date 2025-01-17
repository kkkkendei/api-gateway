package com.wuzeyu.gateway.center.domain.manage.model.aggregates;

import java.util.List;
import com.wuzeyu.gateway.center.domain.manage.model.vo.ApplicationSystemVO;

/**
 * @author wuzeyu
 * @description 网关算力配置信息
 * @github github.com/kkkkendei
 */
public class ApplicationSystemRichInfo {

    /** 网关ID */
    private String gatewayId;

    /** 系统列表 */
    private List<ApplicationSystemVO> applicationSystemVOList;

    public ApplicationSystemRichInfo() {}

    public ApplicationSystemRichInfo(String gatewayId, List<ApplicationSystemVO> applicationSystemVOList) {
        this.gatewayId = gatewayId;
        this.applicationSystemVOList = applicationSystemVOList;
    }

    public String getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }

    public List<ApplicationSystemVO> getApplicationSystemVOList() {
        return applicationSystemVOList;
    }

    public void setApplicationSystemVOList(List<ApplicationSystemVO> applicationSystemVOList) {
        this.applicationSystemVOList = applicationSystemVOList;
    }
}
