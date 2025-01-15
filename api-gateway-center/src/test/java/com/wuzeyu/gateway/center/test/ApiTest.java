package com.wuzeyu.gateway.center.test;


import com.alibaba.fastjson.JSON;
import com.wuzeyu.gateway.center.application.IConfigManageService;
import com.wuzeyu.gateway.center.domain.manage.model.ApiData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wuzeyu
 * @description 单元测试
 * @github github.com/kkkkendei
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

    private Logger LOG = LoggerFactory.getLogger(ApiTest.class);

    @Resource
    private IConfigManageService apiService;

    @Test
    public void test() {
        List<ApiData> apiDataList = apiService.queryHttpStatementList();
        LOG.info("测试结果：{}", JSON.toJSONString(apiDataList));
    }

}
