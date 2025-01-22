package com.wuzeyu.gateway.sdk.config;


import com.wuzeyu.gateway.sdk.application.GatewaySDKApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wuzeyu
 * @description 网关SDK配置服务
 * @github github.com/kkkkendei
 */
@Configuration
@EnableConfigurationProperties(GatewaySDKServiceProperties.class)
public class GatewaySDKAutoConfig {

    private Logger LOG = LoggerFactory.getLogger(GatewaySDKAutoConfig.class);

    @Bean
    public GatewaySDKApplication gatewaySDKApplication() {
        return null;
    }

}
