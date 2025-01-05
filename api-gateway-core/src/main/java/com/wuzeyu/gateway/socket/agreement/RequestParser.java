package com.wuzeyu.gateway.socket.agreement;

import io.netty.handler.codec.http.FullHttpRequest;

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
