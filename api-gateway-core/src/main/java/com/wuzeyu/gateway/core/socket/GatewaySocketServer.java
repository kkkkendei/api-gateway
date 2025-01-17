package com.wuzeyu.gateway.core.socket;

import com.wuzeyu.gateway.core.session.Configuration;
import com.wuzeyu.gateway.core.session.GatewaySessionFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Callable;

/**
 * @author wuzeyu
 * @description 网关会话服务
 * @github github.com/kkkkendei
 */
public class GatewaySocketServer implements Callable<Channel> {

    private final Logger LOG = LoggerFactory.getLogger(GatewaySocketServer.class);

    private final EventLoopGroup boss = new NioEventLoopGroup(1);

    private final EventLoopGroup worker = new NioEventLoopGroup();

    private Channel channel;

    private GatewaySessionFactory gatewaySessionFactory;

    private final Configuration configuration;

    public GatewaySocketServer(Configuration configuration, GatewaySessionFactory gatewaySessionFactory) {
        this.gatewaySessionFactory = gatewaySessionFactory;
        this.configuration = configuration;
    }

    @Override
    public Channel call() throws Exception {

        ChannelFuture f = null;

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childHandler(new GatewayChannelInitializer(configuration, gatewaySessionFactory));
            f = bootstrap.bind(new InetSocketAddress(7397)).syncUninterruptibly();
            this.channel = f.channel();
        } catch (Exception e) {
            LOG.error("socket server start error" + e);
        } finally {
            if (f != null && f.isSuccess()) {
                LOG.info("socket server start done");
            } else {
                LOG.error("socket server start error");
            }
        }

        return channel;
    }
}
