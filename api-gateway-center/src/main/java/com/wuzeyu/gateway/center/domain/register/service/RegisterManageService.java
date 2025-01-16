package com.wuzeyu.gateway.center.domain.register.service;

import com.wuzeyu.gateway.center.application.IRegisterManageService;
import com.wuzeyu.gateway.center.domain.register.model.vo.ApplicationInterfaceMethodVO;
import com.wuzeyu.gateway.center.domain.register.model.vo.ApplicationInterfaceVO;
import com.wuzeyu.gateway.center.domain.register.model.vo.ApplicationSystemVO;
import com.wuzeyu.gateway.center.domain.register.repository.IRegisterManageRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wuzeyu
 * @description api注册服务
 * @github github.com/kkkkendei
 */
@Service
public class RegisterManageService implements IRegisterManageService {

    @Resource
    private IRegisterManageRepository registerManageRepository;

    @Override
    public void registerApplication(ApplicationSystemVO applicationSystemVO) {
        registerManageRepository.registerApplication(applicationSystemVO);
    }

    @Override
    public void registerApplicationInterface(ApplicationInterfaceVO applicationInterfaceVO) {
        registerManageRepository.registerApplicationInterface(applicationInterfaceVO);
    }

    @Override
    public void registerApplicationInterfaceMethod(ApplicationInterfaceMethodVO applicationInterfaceMethodVO) {
        registerManageRepository.registerApplicationInterfaceMethod(applicationInterfaceMethodVO);
    }

}
