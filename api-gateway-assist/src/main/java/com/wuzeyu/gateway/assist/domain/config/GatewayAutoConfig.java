package com.wuzeyu.gateway.assist.domain.config;

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
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.util.Map;
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
    public RedisConnectionFactory redisConnectionFactory(GatewayServiceProperties properties, GatewayCenterService gatewayCenterService) {

        // 拉取注册中心的 Redis 配置信息
        Map<String, String> redisConfig = gatewayCenterService.queryRedisConfig(properties.getAddress());
        // 构建 Redis 服务
        RedisStandaloneConfiguration standaloneConfig = new RedisStandaloneConfiguration();
        standaloneConfig.setHostName(redisConfig.get("host"));
        standaloneConfig.setPort(Integer.parseInt(redisConfig.get("port")));
        // 默认配置信息；一般可以抽取出来
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(100);
        poolConfig.setMaxWaitMillis(30 * 1000);
        poolConfig.setMinIdle(20);
        poolConfig.setMaxIdle(40);
        poolConfig.setTestWhileIdle(true);
        // 创建 Redis 配置
        JedisClientConfiguration clientConfig = JedisClientConfiguration.builder()
                .connectTimeout(Duration.ofSeconds(2))
                .clientName("api-gateway-assist-redis-" + properties.getGatewayId())
                .usePooling().poolConfig(poolConfig).build();
        // 实例化 Redis 链接对象
        return new JedisConnectionFactory(standaloneConfig, clientConfig);

    }

    @Bean
    public RedisMessageListenerContainer container(GatewayServiceProperties properties, RedisConnectionFactory redisConnectionFactory, MessageListenerAdapter msgAgreementListenerAdapter) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);

        container.addMessageListener(msgAgreementListenerAdapter, new PatternTopic(properties.getGatewayId()));
        return container;

    }

    @Bean
    public MessageListenerAdapter msgAgreementListenerAdapter(GatewayApplication gatewayApplication) {
        return new MessageListenerAdapter(gatewayApplication, "receiveMessage");
    }

    @Bean
    public GatewayCenterService registerGatewayService() {
        return new GatewayCenterService();
    }

    @Bean
    public GatewayApplication gatewayApplication(GatewayServiceProperties gatewayServiceProperties, GatewayCenterService gatewayCenterService, com.wuzeyu.gateway.core.session.Configuration configuration, Channel channel) {
        return new GatewayApplication(gatewayServiceProperties, gatewayCenterService, configuration, channel);
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
