package com.wuzeyu.gateway.center.interfaces;


import com.wuzeyu.gateway.center.application.IApiService;
import com.wuzeyu.gateway.center.domain.model.ApiData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wuzeyu
 * @description 网关接口服务
 * @github github.com/kkkkendei
 */
@RestController
@RequestMapping("/api")
public class ApiGatewayController {

    private Logger LOG = LoggerFactory.getLogger(ApiGatewayController.class);

    @Resource
    private IApiService apiService;

    @GetMapping(value = "list", produces = "application/json;charset=utf-8")
    public List<ApiData> getAnswerMap() {
        return apiService.queryHttpStatementList();
    }

}
