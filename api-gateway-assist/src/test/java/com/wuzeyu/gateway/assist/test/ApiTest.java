package com.wuzeyu.gateway.assist.test;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuzeyu.gateway.assist.common.Result;
import com.wuzeyu.gateway.assist.domain.model.aggregates.ApplicationSystemRichInfo;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class ApiTest {

    public static void main(String[] args) {
        System.out.println("Hi Api Gateway");
    }

    @Test
    public void test_register_gateway() {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("groupId", "10001");
        paramMap.put("gatewayId", "api-gateway-g4");
        paramMap.put("gatewayName", "电商配送网关");
        paramMap.put("gatewayAddress", "127.0.0.1");

        String resultStr = HttpUtil.post("http://localhost:8080/wg/admin/config/registerGateway", paramMap, 3500);
        System.out.println(resultStr);

        Result result = JSON.parseObject(resultStr, Result.class);
        System.out.println(result.getCode());
    }

    @Test
    public void test_pullApplicationSystemRichInfo() {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("gatewayId", "api-gateway-g4");
        String resultStr = HttpUtil.post("http://localhost:8080/wg/admin/config/queryApplicationSystemRichInfo", paramMap, 3500);
        Result<ApplicationSystemRichInfo> result = JSON.parseObject(resultStr, new TypeReference<Result<ApplicationSystemRichInfo>>(){});
        System.out.println(JSON.toJSONString(result.getData()));
    }

}
