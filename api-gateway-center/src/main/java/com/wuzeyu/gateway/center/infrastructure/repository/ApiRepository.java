package com.wuzeyu.gateway.center.infrastructure.repository;

import com.wuzeyu.gateway.center.domain.manage.model.ApiData;
import com.wuzeyu.gateway.center.domain.manage.repository.IApiRepository;
import com.wuzeyu.gateway.center.infrastructure.dao.IHttpStatementDAO;
import com.wuzeyu.gateway.center.infrastructure.po.HttpStatement;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wuzeyu
 * @description 仓储实现
 * @github github.com/kkkkendei
 */
@Component
public class ApiRepository implements IApiRepository {

    @Resource
    private IHttpStatementDAO httpStatementDAO;

    @Override
    public List<ApiData> queryHttpStatementList() {

        List<ApiData> res = new ArrayList<>();
        List<HttpStatement> httpStatements = httpStatementDAO.queryHttpStatementList();
        httpStatements.forEach(httpStatement -> {
            ApiData apiData = new ApiData();
            apiData.setApplication(httpStatement.getApplication());
            apiData.setInterfaceName(httpStatement.getInterfaceName());
            apiData.setMethodName(httpStatement.getMethodName());
            apiData.setParameterType(httpStatement.getParameterType());
            apiData.setUri(httpStatement.getUri());
            apiData.setHttpCommandType(httpStatement.getHttpCommandType());
            apiData.setAuth(httpStatement.getAuth());
            res.add(apiData);
        });
        return res;

    }

}
