package com.wuzeyu.gateway.core.socket.handlers;


import com.wuzeyu.gateway.core.bind.IGenericReference;
import com.wuzeyu.gateway.core.session.GatewaySession;
import com.wuzeyu.gateway.core.session.GatewaySessionFactory;
import com.wuzeyu.gateway.core.socket.BaseHandler;
import com.wuzeyu.gateway.core.socket.agreement.RequestParser;
import com.wuzeyu.gateway.core.executor.result.SessionResult;
import com.wuzeyu.gateway.core.socket.agreement.AgreementConstants;
import com.wuzeyu.gateway.core.socket.agreement.GatewayResultMessage;
import com.wuzeyu.gateway.core.socket.agreement.ResponseParser;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author wuzeyu
 * @description 协议数据处理
 * @github github.com/kkkkendei
 */
public class ProtocolDataHandler extends BaseHandler<FullHttpRequest> {

    private final Logger LOG = LoggerFactory.getLogger(ProtocolDataHandler.class);

    private final GatewaySessionFactory gatewaySessionFactory;

    public ProtocolDataHandler(GatewaySessionFactory gatewaySessionFactory) {
        this.gatewaySessionFactory = gatewaySessionFactory;
    }

    @Override
    protected void session(ChannelHandlerContext context, Channel channel, FullHttpRequest request) {

        LOG.info("网关接收请求【信息】 uri: {} method: {}", request.uri(), request.method());

        try {
            // 解析参数
            RequestParser requestParser = new RequestParser(request);
            String uri = requestParser.getUri();
            if (uri == null) {
                return;
            }
            Map<String, Object> args = requestParser.parse();

            // 调用会话服务
            GatewaySession gatewaySession = gatewaySessionFactory.openSession(uri);
            IGenericReference reference = gatewaySession.getMapper();
            SessionResult res = reference.$invoke(args);

            // 封装返回结果
            DefaultFullHttpResponse response = new ResponseParser().parse("0000".equals(res.getCode()) ? GatewayResultMessage.buildSuccess(res.getData()) : GatewayResultMessage.buildError(AgreementConstants.ResponseCode._404.getCode(), "网关协议调用失败！"));
            channel.writeAndFlush(response);
        } catch (Exception e) {
            // 封装返回结果
            DefaultFullHttpResponse response = new ResponseParser().parse(GatewayResultMessage.buildError(AgreementConstants.ResponseCode._502.getCode(), "网关协议调用失败！" + e.getMessage()));
            channel.writeAndFlush(response);
        }


    }
}
