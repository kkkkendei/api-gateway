package com.wuzeyu.gateway.executor;

import com.alibaba.fastjson.JSON;
import com.wuzeyu.gateway.datasource.Connection;
import com.wuzeyu.gateway.executor.result.GatewayResult;
import com.wuzeyu.gateway.mapping.HttpStatement;
import com.wuzeyu.gateway.session.Configuration;
import com.wuzeyu.gateway.type.SimpleTypeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

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

    @Override
    public GatewayResult exec(HttpStatement httpStatement, Map<String, Object> params) throws  Exception{

        //参数处理，后续一些参数校验也可以在这里封装
        String methodName = httpStatement.getMethodName();
        String parameterType = httpStatement.getParameterType();
        String[] parameterTypes = new String[]{parameterType};
        Object[] args = SimpleTypeRegistry.isSimpleType(parameterType) ? params.values().toArray() : new Object[]{parameterType};
        LOG.info("执行调用 method：{}#{}.{}({}) args：{}", httpStatement.getApplication(), httpStatement.getInterfaceName(), httpStatement.getMethodName(), JSON.toJSONString(parameterTypes), JSON.toJSONString(args));
        //抽象方法
        try {
            Object data = doExec(methodName, parameterTypes, args);
            return GatewayResult.buildSuccess(data);
        } catch (Exception e) {
            return GatewayResult.buildError(e.getMessage());
        }

    }

    protected  abstract Object doExec(String name, String[] parameterTypes, Object[] args);

}
