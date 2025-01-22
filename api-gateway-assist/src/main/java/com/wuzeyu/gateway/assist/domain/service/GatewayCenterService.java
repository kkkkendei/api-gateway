package com.wuzeyu.gateway.assist.domain.service;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuzeyu.gateway.assist.GatewayException;
import com.wuzeyu.gateway.assist.common.Result;
import com.wuzeyu.gateway.assist.domain.model.aggregates.ApplicationSystemRichInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wuzeyu
 * @description 网关注册服务
 * @github github.com/kkkkendei
 */
public class GatewayCenterService {

    private Logger LOG = LoggerFactory.getLogger(GatewayCenterService.class);

    public void doRegister(String address, String groupId, String gatewayId, String gatewayName, String gatewayAddress) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("groupId", groupId);
        paramMap.put("gatewayId", gatewayId);
        paramMap.put("gatewayName", gatewayName);
        paramMap.put("gatewayAddress", gatewayAddress);
        String res;
        try {
            res = HttpUtil.post(address + "/wg/admin/config/registerGateway", paramMap, 3500);
        } catch (Exception e) {
            LOG.error("网关服务注册异常，链接资源不可用：{}", address + "/wg/admin/config/registerGateway");
            throw e;
        }
        Result result = JSON.parseObject(res, Result.class);
        LOG.info("向网关中心注册网关算力服务 gatewayId：{} gatewayName：{} gatewayAddress：{} 注册结果：{}", gatewayId, gatewayName, gatewayAddress);
        if (! "0000".equals(result.getCode()))
            throw new GatewayException("网关服务注册异常 [gatewayId：" + gatewayId + "] 、[gatewayAddress：" + gatewayAddress + "]");

    }

    public ApplicationSystemRichInfo pullApplicationSystemRichInfo(String address, String gatewayId) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("gatewayId", gatewayId);
        String resStr;
        try {
            resStr = HttpUtil.post(address + "/wg/admin/config/queryApplicationSystemRichInfo", paramMap, 3500);
        } catch (Exception e) {
            LOG.error("网关服务拉取异常，链接资源不可用：{}", address + "/wg/admin/config/queryApplicationSystemRichInfo");
            throw e;
        }
        Result<ApplicationSystemRichInfo> result = JSON.parseObject(resStr, new TypeReference<Result<ApplicationSystemRichInfo>>(){});
        LOG.info("从网关中心拉取应用服务和接口的配置信息到本地完成注册。gatewayId：{}", gatewayId);
        if (!"0000".equals(result.getCode()))
            throw new GatewayException("从网关中心拉取应用服务和接口的配置信息到本地完成注册异常 [gatewayId：" + gatewayId + "]");

        return result.getData();

    }

}
