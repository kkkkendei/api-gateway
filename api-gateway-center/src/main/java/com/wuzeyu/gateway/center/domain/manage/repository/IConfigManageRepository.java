package com.wuzeyu.gateway.center.domain.manage.repository;

import com.wuzeyu.gateway.center.domain.manage.model.ApiData;
import com.wuzeyu.gateway.center.domain.manage.model.vo.GatewayServerDetailVO;
import com.wuzeyu.gateway.center.domain.manage.model.vo.GatewayServerVO;

import java.util.List;

/**
 * @author wuzeyu
 * @description 网关配置仓储
 * @github github.com/kkkkendei
 */
public interface IConfigManageRepository {

    List<GatewayServerVO> queryGatewayServerList();

    boolean registerGatewayServerNode(String groupId, String gatewayId, String gatewayName, String gatewayAddress, Integer available);

    GatewayServerDetailVO queryGatewayServerDetail(String gatewayId, String gatewayAddress);

    boolean updateGatewayStatus(String gatewayId, String gatewayAddress, Integer available);

}
