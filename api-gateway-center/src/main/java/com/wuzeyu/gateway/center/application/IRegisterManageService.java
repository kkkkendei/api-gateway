package com.wuzeyu.gateway.center.application;

import com.wuzeyu.gateway.center.domain.register.model.vo.ApplicationInterfaceMethodVO;
import com.wuzeyu.gateway.center.domain.register.model.vo.ApplicationInterfaceVO;
import com.wuzeyu.gateway.center.domain.register.model.vo.ApplicationSystemVO;

/**
 * @author wuzeyu
 * @description api注册服务
 * @github github.com/kkkkendei
 */
public interface IRegisterManageService {

    void registerApplication(ApplicationSystemVO applicationSystemVO);

    void registerApplicationInterface(ApplicationInterfaceVO applicationInterfaceVO);

    void registerApplicationInterfaceMethod(ApplicationInterfaceMethodVO applicationInterfaceMethodVO);

}
