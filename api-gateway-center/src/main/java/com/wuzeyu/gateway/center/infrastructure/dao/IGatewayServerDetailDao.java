package com.wuzeyu.gateway.center.infrastructure.dao;


import com.wuzeyu.gateway.center.domain.operation.model.vo.GatewayServerDetailDataVO;
import com.wuzeyu.gateway.center.infrastructure.common.OperationRequest;
import com.wuzeyu.gateway.center.infrastructure.po.GatewayServerDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wuzeyu
 * @description
 * @github github.com/kkkkendei
 */
@Mapper
public interface IGatewayServerDetailDao {

    void insert(GatewayServerDetail gatewayServerDetail);

    GatewayServerDetail queryGatewayServerDetail(GatewayServerDetail gatewayServerDetail);

    boolean updateGatewayStatus(GatewayServerDetail gatewayServerDetail);

    List<GatewayServerDetail> queryGatewayServerDetailList();

    List<GatewayServerDetail> queryGatewayServerDetailListByPage(OperationRequest<GatewayServerDetailDataVO> request);

    int queryGatewayServerDetailListCountByPage(OperationRequest<GatewayServerDetailDataVO> request);

}
