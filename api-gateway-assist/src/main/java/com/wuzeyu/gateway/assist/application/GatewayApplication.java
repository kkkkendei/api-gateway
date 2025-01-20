package com.wuzeyu.gateway.assist.application;

import com.alibaba.fastjson.JSON;
import com.wuzeyu.gateway.assist.config.GatewayServiceProperties;
import com.wuzeyu.gateway.assist.domain.model.aggregates.ApplicationSystemRichInfo;
import com.wuzeyu.gateway.assist.domain.model.vo.ApplicationInterfaceMethodVO;
import com.wuzeyu.gateway.assist.domain.model.vo.ApplicationInterfaceVO;
import com.wuzeyu.gateway.assist.domain.model.vo.ApplicationSystemVO;
import com.wuzeyu.gateway.assist.domain.service.GatewayCenterService;
import com.wuzeyu.gateway.core.mapping.HttpCommandType;
import com.wuzeyu.gateway.core.mapping.HttpStatement;
import com.wuzeyu.gateway.core.session.Configuration;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;

import org.springframework.context.event.ContextRefreshedEvent;

import java.util.List;

/**
 * @author wuzeyu
 * @description 网关应用，与 Spring 连接，调用网关注册和接口拉取
 * @github github.com/kkkkendei
 */
public class GatewayApplication implements ApplicationListener<ContextRefreshedEvent> {

    private Logger LOG = LoggerFactory.getLogger(GatewayApplication.class);

    private GatewayServiceProperties properties;

    private GatewayCenterService gatewayCenterService;

    private Configuration configuration;

    public GatewayApplication(GatewayServiceProperties properties, GatewayCenterService gatewayCenterService, Configuration configuration) {
        this.properties = properties;
        this.gatewayCenterService = gatewayCenterService;
        this.configuration = configuration;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        // 注册网关服务，每一个用于转换 HTTP 协议泛化调用到 RPC 接口的网关都是一个算力，这些算力需要配置网关注册中心
        gatewayCenterService.doRegister(properties.getAddress(), properties.getGroupId(), properties.getGatewayId(), properties.getGatewayName(), properties.getGatewayAddress());

        // 拉取网关配置
        ApplicationSystemRichInfo applicationSystemRichInfo = gatewayCenterService.pullApplicationSystemRichInfo(properties.getAddress(), properties.getGatewayId());
        List<ApplicationSystemVO> applicationSystemVOList = applicationSystemRichInfo.getApplicationSystemVOList();
        for (ApplicationSystemVO system : applicationSystemVOList) {
            List<ApplicationInterfaceVO> interfaceList = system.getInterfaceList();
            for (ApplicationInterfaceVO itf : interfaceList) {
                // 2.1 创建配置信息加载注册
                configuration.registryConfig(system.getSystemId(), system.getSystemRegistry(), itf.getInterfaceId(), itf.getInterfaceVersion());
                List<ApplicationInterfaceMethodVO> methodList = itf.getMethodList();
                // 2.2 注册系统服务接口信息
                for (ApplicationInterfaceMethodVO method : methodList) {
                    HttpStatement httpStatement = new HttpStatement(
                            system.getSystemId(),
                            itf.getInterfaceId(),
                            method.getMethodId(),
                            method.getParameterType(),
                            method.getUri(),
                            HttpCommandType.valueOf(method.getHttpCommandType()),
                            method.isAuth());
                    configuration.addMapper(httpStatement);
                    LOG.info("网关服务注册映射 系统：{} 接口：{} 方法：{}", system.getSystemId(), itf.getInterfaceId(), method.getMethodId());
                }
            }
        }
    }

}
