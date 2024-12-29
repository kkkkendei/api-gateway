package com.wuzeyu.gateway.bind;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;

public class GenericReferenceProxy implements MethodInterceptor {

    /**
     * RPC泛化调用服务
     */
    private final GenericService genericService;

    /**
     * RPC泛化调用方法
     */
    private final String methodName;

    public GenericReferenceProxy(GenericService genericService, String methodName) {
        this.genericService = genericService;
        this.methodName = methodName;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

        Class<?>[] parameterTypes = method.getParameterTypes();
        String[] parameters = new String[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; ++ i) {
            parameters[i] = parameterTypes[i].getName();
        }

        return genericService.$invoke(methodName, parameters, args);
    }


}
