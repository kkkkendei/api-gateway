package com.wuzeyu.gateway.bind;


import net.sf.cglib.core.Signature;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InterfaceMaker;
import org.apache.dubbo.rpc.service.GenericService;
import org.objectweb.asm.Type;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wuzeyu
 * @descrption RPC泛化调用代理工厂,创建代理对象的实现类。
 * @github github.com/kkkkendei
 */
public class GenericReferenceProxyFactory {

    /**
     * RPC 泛化调用服务
     */
    private final GenericService genericService;

    /**
     * 缓存代理对象的实现类
     */
    private final Map<String, IGenericReference> genericReferenceCache = new ConcurrentHashMap<>();

    public GenericReferenceProxyFactory(GenericService genericService) {
        this.genericService = genericService;
    }

    public IGenericReference newInstance(String methodName) {

        return genericReferenceCache.computeIfAbsent(methodName, key -> {

            //泛化调用
            GenericReferenceProxy genericReferenceProxy = new GenericReferenceProxy(genericService, methodName);
            //创建接口
            InterfaceMaker interfaceMaker = new InterfaceMaker();
            interfaceMaker.add(
                    new Signature( //描述方法的签名，封装下面这些信息
                            methodName, //方法名
                            Type.getType(String.class), //出参类型
                            new Type[]{Type.getType(String.class)}), //入参类型数组
                    null);
            Class<?> interfaceClass = interfaceMaker.create();
            //代理对象
            Enhancer enhancer = new Enhancer();
            //设置目标类
            enhancer.setSuperclass(Object.class);
            // IGenericReference 统一泛化调用接口     interfaceClass 根据泛化调用注册信息创建的接口，建立HTTP -> RPC关联
            enhancer.setInterfaces(new Class[]{IGenericReference.class, interfaceClass});
            //设置方法拦截类
            enhancer.setCallback(genericReferenceProxy);

            //返回代理对象-泛化调用
            return (IGenericReference) enhancer.create();
        });

    }


}
