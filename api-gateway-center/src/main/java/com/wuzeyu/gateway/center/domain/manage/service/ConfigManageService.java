package com.wuzeyu.gateway.center.domain.manage.service;


import com.wuzeyu.gateway.center.application.IConfigManageService;
import com.wuzeyu.gateway.center.domain.manage.model.aggregates.ApplicationSystemRichInfo;
import com.wuzeyu.gateway.center.domain.manage.model.vo.*;
import com.wuzeyu.gateway.center.domain.manage.repository.IConfigManageRepository;
import com.wuzeyu.gateway.center.infrastructure.common.Constants;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wuzeyu
 * @descripiton 网关配置服务
 * @github github.com/kkkkendei
 */
@Service
public class ConfigManageService implements IConfigManageService {

    @Resource
    private IConfigManageRepository configManageRepository;

    @Override
    public List<GatewayServerVO> queryGatewayServerList() {
        return configManageRepository.queryGatewayServerList();
    }

    @Override
    public boolean registerGatewayServerNode(String groupId, String gatewayId, String gatewayName, String gatewayAddress) {
        GatewayServerDetailVO gatewayServerDetailVO = configManageRepository.queryGatewayServerDetail(gatewayId, gatewayAddress);
        if (null == gatewayServerDetailVO) {
            try {
                return configManageRepository.registerGatewayServerNode(groupId, gatewayId, gatewayName, gatewayAddress, Constants.GatewayStatus.Available);
            } catch (DuplicateKeyException e) {
                return configManageRepository.updateGatewayStatus(gatewayId, gatewayAddress, Constants.GatewayStatus.Available);
            }
        } else {
            return configManageRepository.updateGatewayStatus(gatewayId, gatewayAddress, Constants.GatewayStatus.Available);
        }
    }

    @Override
    public ApplicationSystemRichInfo queryApplicationSystemRichInfo(String gatewayId, String systemId) {

        //  查询出网关ID对应的关联系统ID集合。也就是一个网关ID会被分配一些系统RPC服务注册进来，需要把这些服务查询出来。
        List<String> systemIdList = new ArrayList<>();
        if (systemId == null || systemId.equals("")){
            systemIdList = configManageRepository.queryGatewayDistributionSystemIdList(gatewayId);
        } else {
            systemIdList.add(systemId);
        }

        //  查询系统ID对应的系统列表信息
        List<ApplicationSystemVO> applicationSystemVOList = configManageRepository.queryApplicationSystemList(systemIdList);

        // 1. 查询所有系统的接口信息
        Map<String, List<ApplicationInterfaceVO>> systemInterfaceMap = new HashMap<>();
        for (ApplicationSystemVO applicationSystemVO : applicationSystemVOList) {
            List<ApplicationInterfaceVO> applicationInterfaceVOList = configManageRepository.queryApplicationInterfaceList(applicationSystemVO.getSystemId());
            systemInterfaceMap.put(applicationSystemVO.getSystemId(), applicationInterfaceVOList);
            applicationSystemVO.setInterfaceList(applicationInterfaceVOList);
        }

        // 2. 查询所有接口的方法信息
        Map<String, List<ApplicationInterfaceMethodVO>> interfaceMethodMap = new HashMap<>();
        for (List<ApplicationInterfaceVO> applicationInterfaceVOList : systemInterfaceMap.values()) {
            for (ApplicationInterfaceVO applicationInterfaceVO : applicationInterfaceVOList) {
                List<ApplicationInterfaceMethodVO> applicationInterfaceMethodVOList = configManageRepository.queryApplicationInterfaceMethodList(applicationInterfaceVO.getSystemId(), applicationInterfaceVO.getInterfaceId());
                interfaceMethodMap.put(applicationInterfaceVO.getInterfaceId(), applicationInterfaceMethodVOList);

            }
        }

        // 3. 组装数据
        for (ApplicationSystemVO applicationSystemVO : applicationSystemVOList) {
            List<ApplicationInterfaceVO> applicationInterfaceVOList = systemInterfaceMap.get(applicationSystemVO.getSystemId());
            for (ApplicationInterfaceVO applicationInterfaceVO : applicationInterfaceVOList) {
                List<ApplicationInterfaceMethodVO> applicationInterfaceMethodVOList = interfaceMethodMap.get(applicationInterfaceVO.getInterfaceId());
                applicationInterfaceVO.setMethodList(applicationInterfaceMethodVOList);
            }
            applicationSystemVO.setInterfaceList(applicationInterfaceVOList);
        }

        return new ApplicationSystemRichInfo(gatewayId, applicationSystemVOList);

    }

    @Override
    public String queryGatewayDistribution(String systemId) {
        return configManageRepository.queryGatewayDistribution(systemId);
    }


}
