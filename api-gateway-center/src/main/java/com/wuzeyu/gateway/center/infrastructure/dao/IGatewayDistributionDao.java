package com.wuzeyu.gateway.center.infrastructure.dao;


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

}
