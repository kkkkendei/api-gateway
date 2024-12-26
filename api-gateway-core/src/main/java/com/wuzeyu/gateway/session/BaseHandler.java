package com.wuzeyu.gateway.session;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public abstract class BaseHandler<T> extends SimpleChannelInboundHandler<T> {

    @Override
    protected void channelRead0(ChannelHandlerContext context, T msg) throws Exception {
        session(context, context.channel(), msg);
    }

    protected abstract void session(ChannelHandlerContext context, final Channel channel, T request);

}
