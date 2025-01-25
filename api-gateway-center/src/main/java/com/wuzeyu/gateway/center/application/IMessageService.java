package com.wuzeyu.gateway.center.application;

import java.util.Map;

/**
 * @author wuzeyu
 * @description 消息服务
 * @github github.com/kkkkendei
 */
public interface IMessageService {

    Map<String, String> queryRedisConfig();

    void pushMessage(String gatewayId, Object message);

}
