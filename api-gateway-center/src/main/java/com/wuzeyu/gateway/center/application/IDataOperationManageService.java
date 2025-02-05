package com.wuzeyu.gateway.center.application;


import com.wuzeyu.gateway.center.domain.operation.model.vo.*;
import com.wuzeyu.gateway.center.infrastructure.common.OperationRequest;
import com.wuzeyu.gateway.center.infrastructure.common.OperationResult;

/**
 * @author wuzeyu
 * @description 网关运营数据管理
 * @github github.com/kkkkendei
 */
public interface IDataOperationManageService {

    OperationResult<GatewayServerDataVO> queryGatewayServer(OperationRequest<String> request);

    OperationResult<ApplicationSystemDataVO> queryApplicationSystem(OperationRequest<ApplicationSystemDataVO> request);

    OperationResult<ApplicationInterfaceDataVO> queryApplicationInterface(OperationRequest<ApplicationInterfaceDataVO> request);

    OperationResult<ApplicationInterfaceMethodDataVO> queryApplicationInterfaceMethod(OperationRequest<ApplicationInterfaceMethodDataVO> request);

    OperationResult<GatewayServerDetailDataVO> queryGatewayServerDetail(OperationRequest<GatewayServerDetailDataVO> request);

    OperationResult<GatewayDistributionDataVO> queryGatewayDistribution(OperationRequest<GatewayDistributionDataVO> request);

}
