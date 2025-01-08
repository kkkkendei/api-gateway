package com.wuzeyu.gateway.socket.agreement;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * @author wuzeyu
 * @description 请求解析器，解析HTTP请求
 * @github github.com/kkkkendei
 */
public class RequestParser {

    private final FullHttpRequest request;

    public RequestParser(FullHttpRequest request) {
        this.request = request;
    }

    public Map<String, Object> parse() {

        //获取Content-type
        String contentType = getContentType();
        //获取请求类型
        HttpMethod method = request.method();
        if (HttpMethod.GET == method) {

            Map<String, Object> parameterMap = new HashMap<>();
            //解析HTTP请求参数
            QueryStringDecoder decoder = new QueryStringDecoder(request.uri());
            Map<String, List<String>> params = decoder.parameters();
            params.forEach((key, value) -> parameterMap.put(key, value.get(0)));
            return parameterMap;

        } else if (HttpMethod.POST == method) {

            switch (contentType) {
                case "multipart/form-data":
                    Map<String, Object> parameterMap = new HashMap<>();
                    HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(request);
                    decoder.offer(request);
                    decoder.getBodyHttpDatas().forEach(data -> {
                        Attribute attribute = (Attribute) data;
                        try {
                            parameterMap.put(data.getName(), attribute.getValue());
                        } catch (IOException ignore) {

                        }
                    });
                    return parameterMap;

                case "application/json":
                    ByteBuf byteBuf = request.content().copy();
                    if (byteBuf.isReadable()) {
                        String content = byteBuf.toString(StandardCharsets.UTF_8);
                        return JSON.parseObject(content);
                    }
                    break;

                default:
                    throw new RuntimeException("未实现的协议类型 Content-Type: " + contentType);
            }

        }
        throw new RuntimeException("未实现的请求类型 HttpMethod: " + method);

    }

    private String getContentType() {

        Optional<Map.Entry<String, String>> header = request.headers().entries().stream().filter(
                val -> val.getKey().equals("Content-Type")
        ).findAny();
        //提高可读性
        Map.Entry<String, String> entry = header.orElse(null);
        assert entry != null;
        // POST请求参数类型
        String contentType = entry.getValue();
        int idx = contentType.indexOf(";");
        if (idx > 0) {
            return contentType.substring(0, idx);
        } else {
            return contentType;
        }

    }

}
