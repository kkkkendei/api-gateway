package com.wuzeyu.gateway.session.defaults;

import com.wuzeyu.gateway.bind.IGenericReference;
import com.wuzeyu.gateway.datasource.Connection;
import com.wuzeyu.gateway.datasource.Datasource;
import com.wuzeyu.gateway.mapping.HttpStatement;
import com.wuzeyu.gateway.session.Configuration;
import com.wuzeyu.gateway.session.GatewaySession;
import com.wuzeyu.gateway.type.SimpleTypeRegistry;

import java.util.Map;

public class DefaultGatewaySession implements GatewaySession {

    private final Configuration configuration;

    private final String uri;

    private final Datasource datasource;

    public DefaultGatewaySession(Configuration configuration, String uri, Datasource datasource) {
        this.configuration = configuration;
        this.uri = uri;
        this.datasource = datasource;
    }

    @Override
    public Object get(String methodName, Map<String, Object> params) {

        Connection connection = datasource.getConnection();
        HttpStatement httpStatement = configuration.getHttpStatement(uri);
        String parameterType = httpStatement.getParameterType();

        //注意一下
        return connection.execute(methodName,
                new String[]{parameterType},
                new String[]{"name"},
                SimpleTypeRegistry.isSimpleType(parameterType) ? params.values().toArray() : new Object[]{params});

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
