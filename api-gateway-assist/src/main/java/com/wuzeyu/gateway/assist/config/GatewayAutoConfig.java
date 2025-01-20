package com.wuzeyu.gateway.assist.config;

import com.wuzeyu.gateway.assist.application.GatewayApplication;
import com.wuzeyu.gateway.assist.domain.service.GatewayCenterService;
import com.wuzeyu.gateway.core.session.defaults.DefaultGatewaySessionFactory;
import com.wuzeyu.gateway.core.socket.GatewaySocketServer;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
    public GatewayCenterService registerGatewayService() {
        return new GatewayCenterService();
    }

    @Bean
    public GatewayApplication gatewayApplication(GatewayServiceProperties gatewayServiceProperties, GatewayCenterService gatewayCenterService, com.wuzeyu.gateway.core.session.Configuration configuration) {
        return new GatewayApplication(gatewayServiceProperties, gatewayCenterService, configuration);
    }

    /**
     * 创建网关配置对象；Configuration 是用于贯穿整个网关核心通信服务的。
     */
    @Bean
    public com.wuzeyu.gateway.core.session.Configuration gatewayCoreConfiguration(GatewayServiceProperties properties) {

        com.wuzeyu.gateway.core.session.Configuration configuration = new com.wuzeyu.gateway.core.session.Configuration();
        String[] split = properties.getGatewayAddress().split(":");
        configuration.setHostName(split[0].trim());
        configuration.setPort(Integer.parseInt(split[1].trim()));
        return configuration;

    }

    @Bean
    public Channel initGateway(com.wuzeyu.gateway.core.session.Configuration configuration) throws ExecutionException, InterruptedException {

        // 基于配置构建会话工厂
        DefaultGatewaySessionFactory gatewaySessionFactory = new DefaultGatewaySessionFactory(configuration);
        // 创建启动网关网络服务
        GatewaySocketServer server = new GatewaySocketServer(configuration, gatewaySessionFactory);
        Future<Channel> future = Executors.newFixedThreadPool(2).submit(server);
        Channel channel = future.get();
        if (channel == null) throw new RuntimeException("api gateway core netty server start error channel is null");
        while (! channel.isActive()) {
            LOG.info("api gateway core netty server gateway start Ing ...");
            Thread.sleep(500);
        }
        LOG.info("api gateway core netty server gateway start Done! {}", channel.localAddress());
        return channel;

    }

}
