package com.wuzeyu.gateway.center.application;


import com.wuzeyu.gateway.center.domain.model.ApiData;

import java.util.List;

/**
 * @author wuzeyu
 * @description API 服务
 * @github github.com/kkkkendei
 */
public interface IApiService {

    List<ApiData> queryHttpStatementList();

}
