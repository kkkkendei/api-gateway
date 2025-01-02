package com.wuzeyu.gateway.mapping;

/**
 * @author wuzeyu
 * @description 网关接口映射信息
 * @github github.com/kkkkendei
 */
public class HttpStatement {

    //服务名称
    private String application;

    //服务接口；RPC
    private String interfaceName;

    //服务方法；RPC
    private String methodName;

    //网关接口；uri
    private String uri;

    //接口类型：GET,POST,PUT,DELETE
    private HttpCommandType httpCommandType;

    public HttpStatement(String application, String interfaceName, String methodName, String uri, HttpCommandType httpCommandType) {

        this.application = application;
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.uri = uri;
        this.httpCommandType = httpCommandType;

    }

    public String getApplication() {
        return application;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getUri() {
        return uri;
    }

    public HttpCommandType getHttpCommandType() {
        return httpCommandType;
    }
}
