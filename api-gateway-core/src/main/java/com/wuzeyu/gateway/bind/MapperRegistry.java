package com.wuzeyu.gateway.bind;

import com.wuzeyu.gateway.session.Configuration;
import com.wuzeyu.gateway.session.GatewaySession;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.config.utils.ReferenceConfigCache;
import org.apache.dubbo.rpc.service.GenericService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wuzeyu
 * @description 泛化调用注册器
 * @github github.com/kkkkendei
 */
public class MapperRegistry {

    private final Configuration configuration;

    //泛化调用代理工厂
    private final Map<String, MapperProxyFactory> knownMappers = new HashMap<>();

    public MapperRegistry(Configuration configuration) {
        this.configuration = configuration;
    }


    public IGenericReference getMapper(String uri, GatewaySession gatewaySession) {

        MapperProxyFactory mapperProxyFactory = knownMappers.get(uri);
        

    }


    /**
     * 泛化调用服务注册,并保存到knownGenericReferences中
     * @param application 服务：api-gateway-test
     * @param interfaceName 服务的接口： cn.bugstack.gateway.rpc.IActivityBooth
     * @param methodName 方法名：sayHi
     */
    public void addGenericReference(String application, String interfaceName, String methodName) {

        //获取基础服务
        ApplicationConfig applicationConfig = configuration.getApplicationConfig(application);
        RegistryConfig registryConfig = configuration.getRegistryConfig(application);
        ReferenceConfig<GenericService> referenceConfig = configuration.getReferenceConfig(interfaceName);

        //构建Dubbo服务
        DubboBootstrap bootstrap = DubboBootstrap.getInstance();
        bootstrap.application(applicationConfig).registry(registryConfig).reference(referenceConfig).start();
        //获取泛化调用服务
        ReferenceConfigCache cache = ReferenceConfigCache.getCache();
        GenericService genericService = cache.get(referenceConfig);

        //创建并保存泛化工厂
        knownMappers.put(methodName, new MapperProxyFactory(genericService));

    }

    /**
     * 获取泛化调用服务
     * @param methodName 方法：sayHi
     * @return
     */
    public IGenericReference getGenericReference(String methodName) {

        MapperProxyFactory mapperProxyFactory = knownMappers.get(methodName);
        if (mapperProxyFactory == null) {
            throw new RuntimeException(methodName + "is not known to the GenericReferenceRegistry");
        }

        return mapperProxyFactory.newInstance(methodName);

    }

}
