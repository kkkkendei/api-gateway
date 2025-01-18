package com.wuzeyu.gateway.assist.config;

import com.wuzeyu.gateway.assist.application.GatewayApplication;
import com.wuzeyu.gateway.assist.domain.service.RegisterGatewayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wuzeyu
 * @description 配置网关服务
 * @github github.com/kkkkendei
 */
@Configuration
@EnableConfigurationProperties(GatewayServiceProperties.class)
public class GatewayAutoConfig {

    private Logger LOG = LoggerFactory.getLogger(GatewayAutoConfig.class);


    @Bean
    public RegisterGatewayService registerGatewayService() {
        return new RegisterGatewayService();
    }

    @Bean
    public GatewayApplication gatewayApplication(GatewayServiceProperties gatewayServiceProperties, RegisterGatewayService registerGatewayService) {
        return new GatewayApplication(gatewayServiceProperties, registerGatewayService);
    }

}
