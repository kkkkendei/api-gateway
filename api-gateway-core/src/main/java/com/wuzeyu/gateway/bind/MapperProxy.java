package com.wuzeyu.gateway.bind;

import com.wuzeyu.gateway.session.GatewaySession;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.dubbo.rpc.service.GenericService;

import java.lang.reflect.Method;

/**
 * @author wuzeyu
 * @description 泛化调用代理，给HTTP对应的RPC调用做一层代理控制。每调用到一个HTTP对应的网关方法就会以代理的方式调用RPC对应的泛化调用方法上
 * @github github.com/kkkkendei
 */
public class MapperProxy implements MethodInterceptor {

    private GatewaySession gatewaySession;

    private final String uri;

    public MapperProxy(GatewaySession gatewaySession, String uri) {
        this.gatewaySession = gatewaySession;
        this.uri = uri;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

        Class<?>[] parameterTypes = method.getParameterTypes();
        String[] parameters = new String[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; ++ i) {
            parameters[i] = parameterTypes[i].getName();
        }

        // 举例：genericService.$invoke("sayHi", new String[]{"java.lang.String"}, new Object[]{"world"});
        return genericService.$invoke(methodName, parameters, args);
    }


}
