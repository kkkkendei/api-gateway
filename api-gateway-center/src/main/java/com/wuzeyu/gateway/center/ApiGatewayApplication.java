package com.wuzeyu.gateway.center;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author wuzeyu
 * @description 网关注册中心启动服务
 * @github github.com/kkkkendei
 */
@SpringBootApplication
@Configurable
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

}