package com.wuzeyu.gateway.core.bind;

import com.wuzeyu.gateway.core.session.GatewaySession;
import com.wuzeyu.gateway.core.mapping.HttpCommandType;
import com.wuzeyu.gateway.core.session.Configuration;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author wuzeyu
 * @description 绑定请求方法
 * @github github.com/kkkkendei
 */
public class MapperMethod {

    private final String uri;

    private final String methodName;

    private final HttpCommandType httpCommandType;

    public MapperMethod(String uri, Method method, Configuration configuration) {
        this.uri = uri;
        this.methodName = configuration.getHttpStatement(uri).getMethodName();
        this.httpCommandType = configuration.getHttpStatement(uri).getHttpCommandType();
    }

    public Object execute(GatewaySession session, Map<String, Object> params) {
        Object res = null;
        switch (httpCommandType) {
            case GET:
                res = session.get(methodName, params);
                break;
            case POST:
                res = session.post(methodName, params);
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
