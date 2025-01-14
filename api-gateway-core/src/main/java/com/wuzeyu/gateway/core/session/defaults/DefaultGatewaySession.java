package com.wuzeyu.gateway.core.session.defaults;

import com.wuzeyu.gateway.core.bind.IGenericReference;
import com.wuzeyu.gateway.core.session.GatewaySession;
import com.wuzeyu.gateway.core.executor.Executor;
import com.wuzeyu.gateway.core.mapping.HttpStatement;
import com.wuzeyu.gateway.core.session.Configuration;

import java.util.Map;

public class DefaultGatewaySession implements GatewaySession {

    private Configuration configuration;

    private String uri;

    private Executor executor;

    public DefaultGatewaySession(Configuration configuration, String uri, Executor executor) {
        this.configuration = configuration;
        this.uri = uri;
        this.executor = executor;
    }

    @Override
    public Object get(String methodName, Map<String, Object> params) {

        HttpStatement httpStatement = configuration.getHttpStatement(uri);
        try {
            return executor.exec(httpStatement, params);
        } catch (Exception e) {
            throw new RuntimeException("Error exec, Cause: " + e);
        }

    }

    @Override
    public Object post(String method, Map<String, Object> params) {
        return get(method, params);
    }

    @Override
    public IGenericReference getMapper() {
        return configuration.getMapper(uri, this);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }
}
