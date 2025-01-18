package com.wuzeyu.gateway.assist.application;

import com.wuzeyu.gateway.assist.config.GatewayServiceProperties;
import com.wuzeyu.gateway.assist.domain.model.aggregates.ApplicationSystemRichInfo;
import com.wuzeyu.gateway.assist.domain.service.RegisterGatewayService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

/**
 * @author wuzeyu
 * @description 网关应用， 与 Spring 连接，调用网关注册和接口拉取
 * @github github.com/kkkkendei
 */
public class GatewayApplication implements ApplicationListener<ContextClosedEvent> {

    private GatewayServiceProperties properties;

    private RegisterGatewayService registerGatewayService;

    public GatewayApplication(GatewayServiceProperties properties, RegisterGatewayService registerGatewayService) {
        this.properties = properties;
        this.registerGatewayService = registerGatewayService;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {

        // 注册网关服务，每一个用于转换 HTTP 协议泛化调用到 RPC 接口的网关都是一个算力，这些算力需要配置网关注册中心
        registerGatewayService.doRegister(properties.getAddress(), properties.getGroupId(), properties.getGatewayId(), properties.getGatewayName(), properties.getGatewayAddress());

        ApplicationSystemRichInfo applicationSystemRichInfo = registerGatewayService.pullApplicationSystemRichInfo(properties.getAddress(), properties.getGatewayId());
        System.out.println(applicationSystemRichInfo.toString());

    }

}
