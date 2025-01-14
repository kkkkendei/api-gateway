package com.wuzeyu.gateway.core.bind;


import com.wuzeyu.gateway.core.mapping.HttpStatement;
import com.wuzeyu.gateway.core.session.GatewaySession;
import net.sf.cglib.core.Signature;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InterfaceMaker;
import org.objectweb.asm.Type;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wuzeyu
 * @descrption RPC泛化调用代理工厂,创建代理对象的实现类。
 * @github github.com/kkkkendei
 */
public class MapperProxyFactory {

    /**
     * RPC 泛化调用服务
     */
    private String uri;

    /**
     * 缓存代理对象的实现类
     */
    private final Map<String, IGenericReference> genericReferenceCache = new ConcurrentHashMap<>();

    public MapperProxyFactory(String uri) {
        this.uri = uri;
    }


    public IGenericReference newInstance(GatewaySession gatewaySession) {

        return genericReferenceCache.computeIfAbsent(uri, key -> {
            HttpStatement httpStatement = gatewaySession.getConfiguration().getHttpStatement(uri);
            //泛化调用
            MapperProxy genericReferenceProxy = new MapperProxy(gatewaySession, uri);
            //创建接口
            InterfaceMaker interfaceMaker = new InterfaceMaker();
            interfaceMaker.add(new Signature(httpStatement.getMethodName(), Type.getType(String.class), new Type[]{Type.getType(String.class)}), null);
            Class<?> interfaceClass = interfaceMaker.create();
            //代理对象
            Enhancer enhancer = new Enhancer();
            //设置目标类
            enhancer.setSuperclass(Object.class);
            // IGenericReference 统一泛化调用接口
            // interfaceClass 根据泛化调用注册信息创建的接口，建立HTTP -> RPC关联
            enhancer.setInterfaces(new Class[]{IGenericReference.class, interfaceClass});
            //设置方法拦截类
            enhancer.setCallback(genericReferenceProxy);

            //返回代理对象-泛化调用
            return (IGenericReference) enhancer.create();

        });

    }


}
