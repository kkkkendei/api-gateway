package com.wuzeyu.gateway.socket.handlers;

import com.wuzeyu.gateway.bind.IGenericReference;
import com.wuzeyu.gateway.mapping.HttpStatement;
import com.wuzeyu.gateway.session.Configuration;
import com.wuzeyu.gateway.session.GatewaySession;
import com.wuzeyu.gateway.session.GatewaySessionFactory;
import com.wuzeyu.gateway.socket.BaseHandler;
import com.wuzeyu.gateway.socket.agreement.AgreementConstants;
import com.wuzeyu.gateway.socket.agreement.GatewayResultMessage;
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

    private final Configuration configuration;

    public GatewayServerHandler(Configuration configuration) {
        this.configuration = configuration;
    }


    @Override
    protected void session(ChannelHandlerContext context, final Channel channel, FullHttpRequest request) {

        LOG.info("网关接收请求【全局】 uri: {} method: {}", request.uri(), request.method());

        try {
            // 解析请求参数
            RequestParser requestParser = new RequestParser(request);
            String uri = requestParser.getUri();
            if (uri == null) return;

            // 保存信息
            HttpStatement httpStatement = configuration.getHttpStatement(uri);
            channel.attr(AgreementConstants.HTTP_STATEMENT).set(httpStatement);

            // 放行服务
            request.retain();
            context.fireChannelRead(request);

        } catch (Exception e) {
            // 封装返回结果
            DefaultFullHttpResponse response = new ResponseParser().parse(GatewayResultMessage.buildError(AgreementConstants.ResponseCode._500.getCode(), "网关协议调用失败" + e.getMessage()));
            channel.writeAndFlush(response);
        }

    }
}
