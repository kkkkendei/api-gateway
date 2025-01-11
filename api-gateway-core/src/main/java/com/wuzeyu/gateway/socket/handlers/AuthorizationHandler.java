package com.wuzeyu.gateway.socket.handlers;


import com.wuzeyu.gateway.mapping.HttpStatement;
import com.wuzeyu.gateway.session.Configuration;
import com.wuzeyu.gateway.socket.BaseHandler;
import com.wuzeyu.gateway.socket.agreement.AgreementConstants;
import com.wuzeyu.gateway.socket.agreement.GatewayResultMessage;
import com.wuzeyu.gateway.socket.agreement.ResponseParser;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wuzeyu
 * @description 鉴权
 * @github github.com/kkkkendei
 */
public class AuthorizationHandler extends BaseHandler<FullHttpRequest> {

    private final Logger LOG = LoggerFactory.getLogger(AuthorizationHandler.class);

    private final Configuration configuration;

    public AuthorizationHandler(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    protected void session(ChannelHandlerContext context, Channel channel, FullHttpRequest request) {

        LOG.info("网关接收请求【鉴权】 uri: {} method: {}", request.uri(), request.method());
        try {
            HttpStatement httpStatement = channel.attr(AgreementConstants.HTTP_STATEMENT).get();
            if (httpStatement.isAuth()) {
                try {
                    // 鉴权信息
                    String uId = request.headers().get("uId");
                    String token = request.headers().get("token");
                    // 鉴权判断
                    if (token == null || token.equals("")) {
                        DefaultFullHttpResponse response = new ResponseParser().parse(GatewayResultMessage.buildError(AgreementConstants.ResponseCode._400.getCode(), "你的 token 不合法！ "));
                        channel.writeAndFlush(response);
                    }
                    // 鉴权处理 shiro + jwt
                    boolean status = configuration.authValidate(uId, token);
                    // 鉴权成功，放行服务
                    if (status) {
                        request.retain();
                        context.fireChannelRead(request);
                    }
                    // 鉴权失败
                    else {
                        DefaultFullHttpResponse response = new ResponseParser().parse(GatewayResultMessage.buildError(AgreementConstants.ResponseCode._403.getCode(), "你没有权限访问此接口!"));
                        channel.writeAndFlush(response);
                    }
                } catch (Exception e) {
                    DefaultFullHttpResponse response = new ResponseParser().parse(GatewayResultMessage.buildError(AgreementConstants.ResponseCode._403.getCode(), "你的鉴权不合法！"));
                    channel.writeAndFlush(response);
                }
            }
            // 不需要鉴权
            else {
                request.retain();
                context.fireChannelRead(request);
            }
        } catch (Exception e) {
            // 封装返回结果
            DefaultFullHttpResponse response = new ResponseParser().parse(GatewayResultMessage.buildError(AgreementConstants.ResponseCode._500.getCode(), "网关协议调用失败! " + e.getMessage()));
            channel.writeAndFlush(response);
        }

    }

}
