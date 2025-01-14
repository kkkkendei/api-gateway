package com.wuzeyu.gateway.center.domain.repository;

import com.wuzeyu.gateway.center.domain.model.ApiData;

import java.util.List;

/**
 * @author wuzeyu
 * @description API 仓储
 * @github github.com/kkkkendei
 */
public interface IApiRepository {

    List<ApiData> queryHttpStatementList();

}
