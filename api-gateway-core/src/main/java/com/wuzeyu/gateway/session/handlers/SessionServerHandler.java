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
        //DefaultFullHttpResponse相当于在构建HTTP会话所需的协议信息，包括头信息、编码、响应体长度、跨域访问等。
        //这些信息中还包括了我们要向网页端返回的数据，也就是 response.content().writeBytes(...) 中写入的数据内容。
        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        //返回信息控制
        response.content().writeBytes(JSON.toJSONBytes("你的访问路径被ken_dei的网关管理了URI: " + request.uri() + SerializerFeature.PrettyFormat));

        //头部信息设置
        HttpHeaders heads = response.headers();
        //返回内容类型
        heads.add(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON + "; charset=UTF-8");
        //响应体的长度
        heads.add(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
        //配置持久连接
        heads.add(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        //配置跨域访问
        heads.add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        heads.add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_HEADERS, "*");
        heads.add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_METHODS, "GET,POST,PUT,DELETE");
        heads.add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");

        channel.writeAndFlush(response);


    }
}
