package com.wuzeyu.gateway.socket.handlers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wuzeyu.gateway.bind.IGenericReference;
import com.wuzeyu.gateway.session.GatewaySession;
import com.wuzeyu.gateway.session.GatewaySessionFactory;
import com.wuzeyu.gateway.socket.agreement.RequestParser;
import io.netty.handler.codec.http.*;
import org.slf4j.LoggerFactory;
import com.wuzeyu.gateway.socket.BaseHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;

import java.util.Map;

/**
 * @author wuzeyu
 * @description 会话服务处理器
 * @github github.com/kkkkendei
 */
public class GatewayServerHandler extends BaseHandler<FullHttpRequest> {

    private final Logger LOG = LoggerFactory.getLogger(GatewayServerHandler.class);

    private final GatewaySessionFactory gatewaySessionFactory;

    public GatewayServerHandler(GatewaySessionFactory gatewaySessionFactory) {
        this.gatewaySessionFactory = gatewaySessionFactory;
    }


    @Override
    protected void session(ChannelHandlerContext context, final Channel channel, FullHttpRequest request) {

        String uri = request.uri();
        LOG.info("网关请求 uri: {} method: {}", uri, request.method());

        //解析请求参数
        Map<String, Object> requestObj = new RequestParser(request).parse();

        //返回信息控制
        int idx = uri.indexOf("?");
        uri = idx > 0 ? uri.substring(0, idx) : uri;

        //服务泛化调用
        if (uri.equals("/favicon.ico")) return;
        GatewaySession gatewaySession = gatewaySessionFactory.openSession(uri);
        IGenericReference reference = gatewaySession.getMapper(uri);
        String res = reference.$invoke(requestObj) + " " + System.currentTimeMillis();

        //返回信息处理
        //DefaultFullHttpResponse相当于在构建HTTP会话所需的协议信息，包括头信息、编码、响应体长度、跨域访问等。
        //这些信息中还包括了我们要向网页端返回的数据，也就是 response.content().writeBytes(...) 中写入的数据内容。
        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        //返回信息控制
        response.content().writeBytes(JSON.toJSONBytes(res, SerializerFeature.PrettyFormat));

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
