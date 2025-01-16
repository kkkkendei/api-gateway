package com.wuzeyu.gateway.assist.service;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.wuzeyu.gateway.assist.GatewayException;
import com.wuzeyu.gateway.assist.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wuzeyu
 * @description 网关注册服务
 * @github github.com/kkkkendei
 */
public class RegisterGatewayService {

    private Logger LOG = LoggerFactory.getLogger(RegisterGatewayService.class);

    public void doRegister(String address, String groupId, String gatewayId, String gatewayName, String gatewayAddress) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("groupId", groupId);
        paramMap.put("gatewayId", gatewayId);
        paramMap.put("gatewayName", gatewayName);
        paramMap.put("gatewayAddress", gatewayAddress);
        String res = HttpUtil.post(address, paramMap, 350);
        Result result = JSON.parseObject(res, Result.class);
        LOG.info("向网关中心注册网关算力服务 gatewayId：{} gatewayName：{} gatewayAddress：{} 注册结果：{}", gatewayId, gatewayName, gatewayAddress);
        if (! "0000".equals(result.getCode()))
            throw new GatewayException("网关服务注册异常 [gatewayId：" + gatewayId + "] 、[gatewayAddress：" + gatewayAddress + "]");

    }

}
