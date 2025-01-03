package com.wuzeyu.gateway.bind;

import com.wuzeyu.gateway.mapping.HttpCommandType;
import com.wuzeyu.gateway.session.Configuration;
import com.wuzeyu.gateway.session.GatewaySession;
import java.lang.reflect.Method;

/**
 * @author wuzeyu
 * @description 绑定请求方法
 * @github github.com/kkkkendei
 */
public class MapperMethod {

    private final String uri;

    private final HttpCommandType httpCommandType;

    public MapperMethod(String uri, Method method, Configuration configuration) {
        this.uri = uri;
        this.httpCommandType = configuration.getHttpStatement(uri).getHttpCommandType();
    }

    public Object execute(GatewaySession session, Object args) {
        Object res = null;
        switch (httpCommandType) {
            case GET:
                res = session.get(uri, args);
                break;
            case POST:
                break;
            case PUT:
                break;
            case DELETE:
                break;
            default:
                throw new RuntimeException("Unknown execution method for:" + httpCommandType);
        }
        return res;
    }

}
