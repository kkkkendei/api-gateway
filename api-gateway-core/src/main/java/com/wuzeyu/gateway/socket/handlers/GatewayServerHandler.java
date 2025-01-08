package com.wuzeyu.gateway.socket.handlers;

import com.wuzeyu.gateway.bind.IGenericReference;
import com.wuzeyu.gateway.session.GatewaySession;
import com.wuzeyu.gateway.session.GatewaySessionFactory;
import com.wuzeyu.gateway.socket.BaseHandler;
import com.wuzeyu.gateway.socket.agreement.RequestParser;
import com.wuzeyu.gateway.socket.agreement.ResponseParser;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author wuzeyu
 * @description 网络协议处理器
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

        LOG.info("网关请求 uri: {} method: {}", request.uri(), request.method());

        //解析请求参数
        RequestParser requestParser = new RequestParser(request);
        String uri = requestParser.getUri();
        if (uri == null) return;
        Map<String, Object> args = requestParser.parse();

        //会话服务
        GatewaySession gatewaySession = gatewaySessionFactory.openSession(uri);
        IGenericReference reference = gatewaySession.getMapper(uri);
        Object res = reference.$invoke(args);

        //返回结果
        DefaultFullHttpResponse response = new ResponseParser().parse(res);
        channel.writeAndFlush(response);

    }
}
