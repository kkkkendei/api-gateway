package com.wuzeyu.gateway.executor;

import com.wuzeyu.gateway.datasource.Connection;
import com.wuzeyu.gateway.session.Configuration;

/**
 * @auhtor wuzeyu
 * @description 执行器实现类
 * @github github.com/kkkkendei
 */
public class SimpleExecutor extends BaseExecutor {

    public SimpleExecutor(Configuration configuration, Connection connection) {
        super(configuration, connection);
    }

    @Override
    protected Object doExec(String name, String[] parameterTypes, Object[] args) {
        //这里参数暂时只传入了一个
        return connection.execute(name, parameterTypes, new String[]{"ignore"}, args);

    }

}
