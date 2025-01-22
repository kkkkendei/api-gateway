package com.wuzeyu.gateway.sdk.annotation;


import java.lang.annotation.*;

/**
 * @author wuzeyu
 * @description 网关API生产者自定义注解
 * @github github.com/kkkkendei
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ApiProducerClazz {

    /** 接口名称 */
    String interfaceName() default "";

    /** 接口版本 */
    String interfaceVersion() default "";

}
