package com.wuzeyu.gateway.session;

import com.wuzeyu.gateway.session.handlers.SessionServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;

import io.netty.handler.codec.http.HttpResponseEncoder;

public class SessionChannelInitializer extends ChannelInitializer<SocketChannel> {

    private final Configuration configuration;

    public SessionChannelInitializer(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {

        //HTTP编解器
        channel.pipeline().addLast(new HttpRequestDecoder());
        channel.pipeline().addLast(new HttpResponseEncoder());

        //处理除了GET请求外的POST请求时候的对象信息，否则拿不到POST请求
        channel.pipeline().addLast(new HttpObjectAggregator(1024 * 1024));

        //处理自己的业务逻辑
        channel.pipeline().addLast(new SessionServerHandler(configuration));

    }

}
