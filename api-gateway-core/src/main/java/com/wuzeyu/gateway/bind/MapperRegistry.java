package com.wuzeyu.gateway.bind;

import com.wuzeyu.gateway.mapping.HttpStatement;
import com.wuzeyu.gateway.session.Configuration;
import com.wuzeyu.gateway.session.GatewaySession;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.config.utils.ReferenceConfigCache;
import org.apache.dubbo.rpc.service.GenericService;

import javax.management.RuntimeErrorException;
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


    /**
     * 获取泛化调用服务
     * @param uri
     * @param gatewaySession 用户处理网关Http请求
     */
    public IGenericReference getMapper(String uri, GatewaySession gatewaySession) {

        MapperProxyFactory mapperProxyFactory = knownMappers.get(uri);
        if (mapperProxyFactory == null) {
            throw new RuntimeException("Uri" + uri + " is not known to the MapperRegistry");
        }
        try {
            return mapperProxyFactory.newInstance(gatewaySession);
        } catch (Exception e) {
            throw new RuntimeException("Error getting mapper instance. Cause:" + e, e);
        }

    }

    public void addMapper(HttpStatement httpStatement) {

        String uri = httpStatement.getUri();
        // 如果重复注册则报错
        if (knownMappers.containsKey(uri)) {
            throw new RuntimeException("Uri" + uri + " is already known to the MapperRegistry");
        }
        knownMappers.put(uri, new MapperProxyFactory(uri));
        // 保存接口映射信息
        configuration.addHttpStatement(httpStatement);

    }




}
