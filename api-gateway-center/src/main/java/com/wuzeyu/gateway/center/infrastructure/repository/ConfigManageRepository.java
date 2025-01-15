package com.wuzeyu.gateway.center.infrastructure.repository;

import com.wuzeyu.gateway.center.domain.manage.model.ApiData;
import com.wuzeyu.gateway.center.domain.manage.model.vo.GatewayServerDetailVO;
import com.wuzeyu.gateway.center.domain.manage.model.vo.GatewayServerVO;
import com.wuzeyu.gateway.center.domain.manage.repository.IConfigManageRepository;
import com.wuzeyu.gateway.center.infrastructure.dao.IGatewayServerDao;
import com.wuzeyu.gateway.center.infrastructure.dao.IGatewayServerDetailDao;
import com.wuzeyu.gateway.center.infrastructure.dao.IHttpStatementDAO;
import com.wuzeyu.gateway.center.infrastructure.po.GatewayServer;
import com.wuzeyu.gateway.center.infrastructure.po.GatewayServerDetail;
import com.wuzeyu.gateway.center.infrastructure.po.HttpStatement;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wuzeyu
 * @description 网关配置仓储实现
 * @github github.com/kkkkendei
 */
@Component
public class ConfigManageRepository implements IConfigManageRepository {

    @Resource
    private IGatewayServerDao gatewayServerDao;
    @Resource
    private IGatewayServerDetailDao gatewayServerDetailDao;

    @Override
    public List<GatewayServerVO> queryGatewayServerList() {
        List<GatewayServer> gatewayServers = gatewayServerDao.queryGatewayServerList();
        List<GatewayServerVO> gatewayServerVOList = new ArrayList<>(gatewayServers.size());
        for (GatewayServer gatewayServer : gatewayServers) {
            GatewayServerVO gatewayServerVO = new GatewayServerVO();
            gatewayServerVO.setGroupId(gatewayServer.getGroupId());
            gatewayServerVO.setGroupName(gatewayServer.getGroupName());
            gatewayServerVOList.add(gatewayServerVO);
        }
        return gatewayServerVOList;
    }

    @Override
    public boolean registerGatewayServerNode(String groupId, String gatewayId, String gatewayName, String gatewayAddress, Integer status) {
        GatewayServerDetail gatewayServerDetail = new GatewayServerDetail();
        gatewayServerDetail.setGroupId(groupId);
        gatewayServerDetail.setGatewayId(gatewayId);
        gatewayServerDetail.setGatewayName(gatewayName);
        gatewayServerDetail.setGatewayAddress(gatewayAddress);
        gatewayServerDetail.setStatus(status);
        gatewayServerDetailDao.insert(gatewayServerDetail);
        return true;
    }

    @Override
    public GatewayServerDetailVO queryGatewayServerDetail(String gatewayId, String gatewayAddress) {
        GatewayServerDetail req = new GatewayServerDetail();
        req.setGatewayId(gatewayId);
        req.setGatewayAddress(gatewayAddress);
        GatewayServerDetail gatewayServerDetail = gatewayServerDetailDao.queryGatewayServerDetail(req);
        if (null == gatewayServerDetail) return null;
        GatewayServerDetailVO gatewayServerDetailVO = new GatewayServerDetailVO();
        gatewayServerDetailVO.setGatewayId(gatewayServerDetail.getGatewayId());
        gatewayServerDetailVO.setGatewayName(gatewayServerDetail.getGatewayName());
        gatewayServerDetailVO.setGatewayAddress(gatewayServerDetail.getGatewayAddress());
        gatewayServerDetailVO.setStatus(gatewayServerDetail.getStatus());
        return gatewayServerDetailVO;
    }

    @Override
    public boolean updateGatewayStatus(String gatewayId, String gatewayAddress, Integer available) {
        GatewayServerDetail gatewayServerDetail = new GatewayServerDetail();
        gatewayServerDetail.setGatewayId(gatewayId);
        gatewayServerDetail.setGatewayAddress(gatewayAddress);
        gatewayServerDetail.setStatus(available);
        return gatewayServerDetailDao.updateGatewayStatus(gatewayServerDetail);
    }

}
