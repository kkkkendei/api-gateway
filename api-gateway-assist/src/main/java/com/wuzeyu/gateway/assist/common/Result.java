package com.wuzeyu.gateway.assist.common;

/**
 * @author wuzeyu
 * @description 统一返回结果
 * @github github.com/kkkkendei
 */
public class Result<T> {

    private String code;

    private String info;

    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
