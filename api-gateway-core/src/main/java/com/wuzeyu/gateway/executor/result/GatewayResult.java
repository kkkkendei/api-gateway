package com.wuzeyu.gateway.executor.result;

/**
 * @author wuzeyu
 * @description 网关调用结果
 * @github github.com/kkkkendei
 */
public class GatewayResult {

    //调用结果的状态码,0000表示调用成功,0001表示调用失败
    private String code;

    //提供调用结果的描述信息,例如"调用成功"
    private String info;

    //包含实际的调用结果数据,可以是任何类型的对象
    private Object data;

    public GatewayResult(String code, String info, Object data) {
        this.code = code;
        this.info = info;
        this.data = data;
    }

    public static GatewayResult buildSuccess(Object data) {
        return new GatewayResult("0000", "调用成功", data);
    }

    public static GatewayResult buildError(Object data) {
        return new GatewayResult("0001", "调用失败", data);
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

    public Object getData() {
        return data;
    }
}
