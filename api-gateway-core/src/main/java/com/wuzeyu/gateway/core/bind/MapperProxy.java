package com.wuzeyu.gateway.core.bind;

import com.wuzeyu.gateway.core.session.GatewaySession;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Map;

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

        MapperMethod mapperMethod = new MapperMethod(uri, method, gatewaySession.getConfiguration());
        //暂时只获取第一个参数
        return mapperMethod.execute(gatewaySession,(Map<String, Object>) args[0]);

    }


}
