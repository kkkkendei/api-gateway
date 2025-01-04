package com.wuzeyu.gateway.session.defaults;

import com.wuzeyu.gateway.bind.IGenericReference;
import com.wuzeyu.gateway.datasource.Connection;
import com.wuzeyu.gateway.datasource.Datasource;
import com.wuzeyu.gateway.session.Configuration;
import com.wuzeyu.gateway.session.GatewaySession;

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
    public Object get(String methodName, Object parameter) {

        Connection connection = datasource.getConnection();

        //注意一下
        return connection.execute(methodName, new String[]{"java.lang.String"}, new String[]{"name"}, new Object[]{parameter});

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
