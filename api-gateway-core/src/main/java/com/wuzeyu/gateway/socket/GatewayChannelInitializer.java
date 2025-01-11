package com.wuzeyu.gateway.socket;


import com.wuzeyu.gateway.session.Configuration;
import com.wuzeyu.gateway.session.GatewaySessionFactory;
import com.wuzeyu.gateway.socket.handlers.GatewayServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;

import io.netty.handler.codec.http.HttpResponseEncoder;

public class GatewayChannelInitializer extends ChannelInitializer<SocketChannel> {

    private final GatewaySessionFactory gatewaySessionFactory;

    private final Configuration configuration;

    public GatewayChannelInitializer(Configuration configuration, GatewaySessionFactory gatewaySessionFactory) {
        this.configuration = configuration;
        this.gatewaySessionFactory = gatewaySessionFactory;
    }

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {

        //HTTP编解器
        channel.pipeline().addLast(new HttpRequestDecoder());
        channel.pipeline().addLast(new HttpResponseEncoder());

        //处理除了GET请求外的POST请求时候的对象信息，否则拿不到POST请求
        channel.pipeline().addLast(new HttpObjectAggregator(1024 * 1024));

        //处理自己的业务逻辑
        channel.pipeline().addLast(new GatewayServerHandler(configuration));

    }

}
