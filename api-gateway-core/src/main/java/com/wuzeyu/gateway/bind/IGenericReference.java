package com.wuzeyu.gateway.bind;

import java.util.Map;

/**
 * @author wuzeyu
 * @description 统一泛化调用接口,可以允许硬编码
 * @github github.com/kkkkendei
 */
public interface IGenericReference {

    String $invoke(Map<String, Object> args);

}
