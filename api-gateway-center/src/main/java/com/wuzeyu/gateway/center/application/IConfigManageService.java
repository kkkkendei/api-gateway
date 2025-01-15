package com.wuzeyu.gateway.center.application;


import com.wuzeyu.gateway.center.domain.manage.model.ApiData;

import java.util.List;

/**
 * @author wuzeyu
 * @description 网关配置服务
 * @github github.com/kkkkendei
 */
public interface IConfigManageService {

    List<ApiData> queryHttpStatementList();

}
