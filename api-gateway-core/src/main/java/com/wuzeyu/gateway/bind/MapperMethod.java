package com.wuzeyu.gateway.bind;

import com.wuzeyu.gateway.mapping.HttpCommandType;
import com.wuzeyu.gateway.session.Configuration;
import jdk.internal.org.objectweb.asm.commons.Method;

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

    public Object execute()

}
