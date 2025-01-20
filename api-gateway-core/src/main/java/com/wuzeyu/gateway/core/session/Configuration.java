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

    // 网关 Netty 服务地址
    private String hostName = "127.0.0.1";

    // 网关 Netty 服务端口
    private int port = 7397;

    private int bossNThreads = 1;

    private int workNThreads = 4;

    private final MapperRegistry registry = new MapperRegistry(this);

    private final Map<String, HttpStatement> httpStatementMap = new HashMap<>();

    private final IAuth auth = new AuthService();

    // RPC 应用服务配置项
    private final Map<String, ApplicationConfig> applicationConfigMap = new HashMap<>();

    // RPC 注册中心配置项
    private final Map<String, RegistryConfig> registryConfigMap = new HashMap<>();

    // RPC 泛化服务配置项
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

    public synchronized void registryConfig(String applicationName, String address, String interfaceName, String version) {

        if (applicationConfigMap.get(applicationName) == null) {
            ApplicationConfig application = new ApplicationConfig();
            application.setName(applicationName);
            application.setQosEnable(false);
            applicationConfigMap.put(applicationName, application);
        }

        if (registryConfigMap.get(applicationName) == null) {
            RegistryConfig registry = new RegistryConfig();
            registry.setAddress(address);
            registry.setRegister(false);
            registryConfigMap.put(applicationName, registry);
        }

        if (referenceConfigMap.get(interfaceName) == null) {
            ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
            reference.setInterface(interfaceName);
            reference.setVersion(version);
            reference.setGeneric("true");
            referenceConfigMap.put(interfaceName, reference);
        }

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

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getBossNThreads() {
        return bossNThreads;
    }

    public void setBossNThreads(int bossNThreads) {
        this.bossNThreads = bossNThreads;
    }

    public int getWorkNThreads() {
        return workNThreads;
    }

    public void setWorkNThreads(int workNThreads) {
        this.workNThreads = workNThreads;
    }

}
