package com.wuzeyu.gateway.center.infrastructure.dao;


import com.wuzeyu.gateway.center.infrastructure.common.OperationRequest;
import com.wuzeyu.gateway.center.infrastructure.po.GatewayServer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wuzeyu
 * @description
 * @github github.com/kkkkendei
 */
@Mapper
public interface IGatewayServerDao {

    List<GatewayServer> queryGatewayServerList();

    List<GatewayServer> queryGatewayServerListByPage(OperationRequest<String> request);

    int queryGatewayServerListCountByPage(OperationRequest<String> request);

}
