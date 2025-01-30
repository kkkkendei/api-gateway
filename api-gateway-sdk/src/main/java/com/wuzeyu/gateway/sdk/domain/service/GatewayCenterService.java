package com.wuzeyu.gateway.sdk.domain.service;


import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.wuzeyu.gateway.sdk.GatewayException;
import com.wuzeyu.gateway.sdk.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @author wuzeyu
 * @description 应用接口注册服务
 * @github github.com/kkkkendei
 */
public class GatewayCenterService {

    private Logger LOG = LoggerFactory.getLogger(GatewayCenterService.class);

    public void doRegisterApplication(String address, String systemId, String systemName, String systemType, String systemRegistry) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("systemId", systemId);
        paramMap.put("systemName", systemName);
        paramMap.put("systemType", systemType);
        paramMap.put("systemRegistry", systemRegistry);
        String res;
        try {
            res = HttpUtil.post(address + "/wg/admin/register/registerApplication", paramMap, 35000);
        } catch (Exception e) {
            LOG.error("应用服务注册异常，链接资源不可用：{}", address + "/wg/admin/register/registerApplication");
            throw e;
        }
        Result<Boolean> result = JSON.parseObject(res, new TypeReference<Result<Boolean>>(){});
        LOG.info("向网关中心注册应用服务 systemId：{} systemName：{} 注册结果：{}", systemId, systemName, res);
        if(! "0000".equals(result.getCode()) && ! "0003".equals(result.getCode()))
            throw new GatewayException("注册应用服务异常 [systemId：" + systemId + "] 、[systemRegistry：" + systemRegistry + "]");
    }

    public void doRegisterApplicationInterface(String address, String systemId, String interfaceId, String interfaceName, String interfaceVersion) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("systemId", systemId);
        paramMap.put("interfaceId", interfaceId);
        paramMap.put("interfaceName", interfaceName);
        paramMap.put("interfaceVersion", interfaceVersion);
        String resultStr;
        try {
            resultStr = HttpUtil.post(address + "/wg/admin/register/registerApplicationInterface", paramMap, 550);
        } catch (Exception e) {
            LOG.error("应用服务接口注册异常，链接资源不可用：{}", address + "/wg/admin/register/registerApplicationInterface");
            throw e;
        }
        Result<Boolean> result = JSON.parseObject(resultStr, new TypeReference<Result<Boolean>>() {
        });
        LOG.info("向网关中心注册应用接口服务 systemId：{} interfaceId：{} interfaceName：{} 注册结果：{}", systemId, interfaceId, interfaceName, resultStr);
        if (!"0000".equals(result.getCode()) && !"0003".equals(result.getCode()))
            throw new GatewayException("向网关中心注册应用接口服务异常 [systemId：" + systemId + "] 、[interfaceId：" + interfaceId + "]");
    }

    public void doRegisterApplicationInterfaceMethod(String address,
                                                     String systemId,
                                                     String interfaceId,
                                                     String methodId,
                                                     String methodName,
                                                     String parameterType,
                                                     String uri,
                                                     String httpCommandType,
                                                     Integer auth) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("systemId", systemId);
        paramMap.put("interfaceId", interfaceId);
        paramMap.put("methodId", methodId);
        paramMap.put("methodName", methodName);
        paramMap.put("parameterType", parameterType);
        paramMap.put("uri", uri);
        paramMap.put("httpCommandType", httpCommandType);
        paramMap.put("auth", auth);

        String resultStr;
        try {
            resultStr = HttpUtil.post(address + "/wg/admin/register/registerApplicationInterfaceMethod", paramMap, 550);
        } catch (Exception e) {
            LOG.error("应用服务接口注册方法异常，链接资源不可用：{}", address + "/wg/admin/register/registerApplicationInterfaceMethod");
            throw e;
        }
        Result<Boolean> result = JSON.parseObject(resultStr, new TypeReference<Result<Boolean>>() {
        });
        LOG.info("向网关中心注册应用接口方法服务 systemId：{} interfaceId：{} methodId：{} 注册结果：{}", systemId, interfaceId, methodId, resultStr);
        if (!"0000".equals(result.getCode()) && !"0003".equals(result.getCode()))
            throw new GatewayException("向网关中心注册应用接口方法服务异常 [systemId：" + systemId + "] 、[interfaceId：" + interfaceId + "]、[methodId：]" + methodId + "]");
    }

    public void doRegisterEvent(String address, String systemId) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("systemId", systemId);
        String resStr;
        try {
            resStr = HttpUtil.post(address + "/wg/admin/register/registerEvent", paramMap, 5500);
        } catch (Exception e) {
            LOG.error("应用服务接口事件方法异常，链接资源不可用：{}", address + "/wg/admin/register/registerEvent");
            throw e;
        }
        Result<Boolean> res = JSON.parseObject(resStr, new TypeReference<Result<Boolean>>(){});
        LOG.info("应用服务接口事件方法 systemId：{} 注册结果：{}", systemId, resStr);
        if (! res.getCode().equals("0000"))
            throw new GatewayException("向网关中心注册应用接口服务异常 [systemId：" + systemId + "] ");

    }

}
