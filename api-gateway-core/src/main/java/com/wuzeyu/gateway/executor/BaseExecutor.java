package com.wuzeyu.gateway.executor;

import com.wuzeyu.gateway.datasource.Connection;
import com.wuzeyu.gateway.session.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wuzeyu
 * @description 执行器抽象基类
 * @github github.com/kkkkendei
 */
public abstract class BaseExecutor implements Executor {

    private Logger LOG = LoggerFactory.getLogger(BaseExecutor.class);

    protected Configuration configuration;

    protected Connection connection;

    public BaseExecutor(Configuration configuration, Connection connection) {
        this.configuration = configuration;
        this.connection = connection;
    }

}
