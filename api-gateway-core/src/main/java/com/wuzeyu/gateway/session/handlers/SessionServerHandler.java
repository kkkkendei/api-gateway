package com.wuzeyu.gateway.session.handlers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.netty.handler.codec.http.*;
import org.slf4j.LoggerFactory;
import com.wuzeyu.gateway.session.BaseHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;

public class SessionServerHandler extends BaseHandler<FullHttpRequest> {

    private final Logger logger = LoggerFactory.getLogger(SessionServerHandler.class);



    @Override
    protected void session(ChannelHandlerContext context, Channel channel, FullHttpRequest request) {

        logger.info("网关请求 uri: {} method: {}", request.uri(), request.method());

        //返回信息处理
        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        //返回信息控制
        response.content().writeBytes(JSON.toJSONBytes("你的访问路径被ken_dei的网关管理了URI: " + request.uri() + SerializerFeature.PrettyFormat));
        //头部信息设置
        HttpHeaders heads = response.headers();
        //返回内容类型
        heads.add(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON + "; charset=UTF-8");
        //响应体的长度
        heads.add(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
        


    }
}
