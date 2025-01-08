package com.wuzeyu.gateway.session.defaults;

import com.wuzeyu.gateway.bind.IGenericReference;
import com.wuzeyu.gateway.datasource.Connection;
import com.wuzeyu.gateway.datasource.Datasource;
import com.wuzeyu.gateway.executor.Executor;
import com.wuzeyu.gateway.mapping.HttpStatement;
import com.wuzeyu.gateway.session.Configuration;
import com.wuzeyu.gateway.session.GatewaySession;
import com.wuzeyu.gateway.type.SimpleTypeRegistry;

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
    public IGenericReference getMapper(String uri) {
        return configuration.getMapper(uri, this);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }
}
