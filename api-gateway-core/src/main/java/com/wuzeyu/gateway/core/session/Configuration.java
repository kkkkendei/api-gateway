package com.wuzeyu.gateway.core.session;


import com.wuzeyu.gateway.core.authorization.IAuth;
import com.wuzeyu.gateway.core.authorization.auth.AuthService;
import com.wuzeyu.gateway.core.bind.IGenericReference;
import com.wuzeyu.gateway.core.bind.MapperRegistry;
import com.wuzeyu.gateway.core.datasource.Connection;
import com.wuzeyu.gateway.core.mapping.HttpStatement;
import com.wuzeyu.gateway.core.executor.Executor;
import com.wuzeyu.gateway.core.executor.SimpleExecutor;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wuzeyu
 * @description 会话生命周期配置项
 * @gitHub github.com/kkkkendei
 */
public class Configuration {

    private final MapperRegistry registry = new MapperRegistry(this);

    private final Map<String, HttpStatement> httpStatementMap = new HashMap<>();

    private final IAuth auth = new AuthService();

    //RPC应用服务配置项
    private final Map<String, ApplicationConfig> applicationConfigMap = new HashMap<>();

    //RPC注册中心配置项
    private final Map<String, RegistryConfig> registryConfigMap = new HashMap<>();

    //RPC泛化服务配置项
    private final Map<String, ReferenceConfig<GenericService>> referenceConfigMap = new HashMap<>();


    public Configuration() {
        // 暂时写在这里，可以放在配置中
        ApplicationConfig application = new ApplicationConfig();
        application.setName("api-gateway-test");
        application.setQosEnable(false);

        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://47.122.26.159:2181?timeout=60000");
        registry.setRegister(false);

        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        reference.setInterface("cn.bugstack.gateway.rpc.IActivityBooth");
        reference.setGeneric("true");
        reference.setVersion("1.0.0");

        applicationConfigMap.put("api-gateway-test", application);
        registryConfigMap.put("api-gateway-test", registry);
        referenceConfigMap.put("cn.bugstack.gateway.rpc.IActivityBooth", reference);

    }

    public IGenericReference getMapper(String uri, GatewaySession gatewaySession) {
        return registry.getMapper(uri, gatewaySession);
    }

    public void addMapper(HttpStatement httpStatement) {
        registry.addMapper(httpStatement);
    }

    public ApplicationConfig getApplicationConfig(String key) {
        return applicationConfigMap.get(key);
    }

    public RegistryConfig getRegistryConfig(String key) {
        return registryConfigMap.get(key);
    }

    public ReferenceConfig<GenericService> getReferenceConfig(String key) {
        return referenceConfigMap.get(key);
    }

    public void addHttpStatement(HttpStatement statement) {
        httpStatementMap.put(statement.getUri(), statement);
    }

    public HttpStatement getHttpStatement(String uri) {
        return httpStatementMap.get(uri);
    }

    public Executor newExecutor(Connection connection) {
        return new SimpleExecutor(this, connection);
    }

    public boolean authValidate(String uId, String token) {
        return auth.validate(uId, token);
    }

}
