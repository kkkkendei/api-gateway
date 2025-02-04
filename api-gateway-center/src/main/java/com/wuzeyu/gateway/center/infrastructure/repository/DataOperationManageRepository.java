package com.wuzeyu.gateway.center.infrastructure.repository;

import com.wuzeyu.gateway.center.domain.operation.model.vo.*;
import com.wuzeyu.gateway.center.domain.operation.repository.IDataOperationManageRepository;
import com.wuzeyu.gateway.center.infrastructure.common.OperationRequest;
import com.wuzeyu.gateway.center.infrastructure.dao.*;
import com.wuzeyu.gateway.center.infrastructure.po.GatewayServer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wuzeyu
 * @description 运营数据查询仓储服务
 * @github github.com/kkkkendei
 */
@Component
public class DataOperationManageRepository implements IDataOperationManageRepository {

    @Resource
    private IGatewayServerDao gatewayServerDao;

    @Resource
    private IGatewayServerDetailDao gatewayServerDetailDao;

    @Resource
    private IGatewayDistributionDao gatewayDistributionDao;

    @Resource
    private IApplicationSystemDao applicationSystemDao;

    @Resource
    private IApplicationInterfaceDao applicationInterfaceDao;

    @Resource
    private IApplicationInterfaceMethodDao applicationInterfaceMethodDao;

    @Override
    public List<GatewayServerDataVO> queryGatewayServerListByPage(OperationRequest<String> request) {

        List<GatewayServer> gatewayServers = gatewayServerDao.queryGatewayServerListByPage(request);
        return null;

    }

    @Override
    public int queryGatewayServerListCountByPage(OperationRequest<String> request) {
        return 0;
    }

    @Override
    public List<ApplicationSystemDataVO> queryApplicationSystemListByPage(OperationRequest<ApplicationSystemDataVO> request) {
        return null;
    }

    @Override
    public int queryApplicationSystemListCountByPage(OperationRequest<ApplicationSystemDataVO> request) {
        return 0;
    }

    @Override
    public List<ApplicationInterfaceDataVO> queryApplicationInterfaceListByPage(OperationRequest<ApplicationInterfaceDataVO> request) {
        return null;
    }

    @Override
    public int queryApplicationInterfaceListCountByPage(OperationRequest<ApplicationInterfaceDataVO> request) {
        return 0;
    }

    @Override
    public List<ApplicationInterfaceMethodDataVO> queryApplicationInterfaceMethodListByPage(OperationRequest<ApplicationInterfaceMethodDataVO> request) {
        return null;
    }

    @Override
    public int queryApplicationInterfaceMethodListCountByPage(OperationRequest<ApplicationInterfaceMethodDataVO> request) {
        return 0;
    }

    @Override
    public List<GatewayServerDetailDataVO> queryGatewayServerDetailListByPage(OperationRequest<GatewayServerDetailDataVO> request) {
        return null;
    }

    @Override
    public int queryGatewayServerDetailListCountByPage(OperationRequest<GatewayServerDetailDataVO> request) {
        return 0;
    }

    @Override
    public List<GatewayDistributionDataVO> queryGatewayDistributionListByPage(OperationRequest<GatewayDistributionDataVO> request) {
        return null;
    }

    @Override
    public int queryGatewayDistributionListCountByPage(OperationRequest<GatewayDistributionDataVO> request) {
        return 0;
    }
}
