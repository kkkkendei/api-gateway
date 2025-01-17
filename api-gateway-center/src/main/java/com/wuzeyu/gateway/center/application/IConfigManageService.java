package com.wuzeyu.gateway.center.application;


import com.wuzeyu.gateway.center.domain.manage.model.vo.GatewayServerVO;

import java.util.List;

/**
 * @author wuzeyu
 * @description 网关配置服务
 * @github github.com/kkkkendei
 */
public interface IConfigManageService {

    List<GatewayServerVO> queryGatewayServerList();

    boolean registerGatewayServerNode(String groupId, String gatewayId, String gatewayName, String gatewayAddress);



}
