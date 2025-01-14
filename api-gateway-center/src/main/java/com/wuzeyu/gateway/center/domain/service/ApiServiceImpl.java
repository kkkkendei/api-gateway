package com.wuzeyu.gateway.center.domain.service;


import com.wuzeyu.gateway.center.application.IApiService;
import com.wuzeyu.gateway.center.domain.model.ApiData;
import com.wuzeyu.gateway.center.domain.repository.IApiRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wuzeyu
 * @description API 服务
 * @github github.com/kkkkendei
 */
@Service
public class ApiServiceImpl implements IApiService {

    @Resource
    private IApiRepository apiRepository;


    @Override
    public List<ApiData> queryHttpStatementList() {
        return apiRepository.queryHttpStatementList();
    }

}
