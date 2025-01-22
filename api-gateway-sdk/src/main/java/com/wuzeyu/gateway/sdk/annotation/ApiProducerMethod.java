package com.wuzeyu.gateway.sdk.annotation;


import java.lang.annotation.*;

/**
 * @author wuzeyu
 * @description 方法注解
 * @github github.com/kkkkendei
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ApiProducerMethod {

    /** 方法名称 */
    String methodName() default "";

    /** 访问路径 */
    String uri() default "";

    /** 接口类型；GET、POST、PUT、DELETE */
    String httpCommandType() default "GET";

    /** 是否认证；true = 1是、false = 0否 */
    int auth() default 0;
    
}
