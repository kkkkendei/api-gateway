package com.wuzeyu.gateway.center.infrastructure.dao;


import com.wuzeyu.gateway.center.domain.operation.model.vo.GatewayDistributionDataVO;
import com.wuzeyu.gateway.center.infrastructure.common.OperationRequest;
import com.wuzeyu.gateway.center.infrastructure.po.GatewayDistribution;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @aurhor wuzeyu
 * @description 网关分配DAO
 * @github github.com/kkkkendei
 */
@Mapper
public interface IGatewayDistributionDao {

    List<String> queryGatewayDistributionSystemIdList(String gatewayId);

    String queryGatewayDistribution(String systemId);

    List<GatewayDistribution> queryGatewayDistributionList();

    List<GatewayDistribution> queryGatewayDistributionListByPage(OperationRequest<GatewayDistributionDataVO> request);

    int queryGatewayDistributionListCountByPage(OperationRequest<GatewayDistributionDataVO> request);

}
