package com.wuzeyu.gateway.center.domain.register.repository;


import com.wuzeyu.gateway.center.domain.register.model.vo.ApplicationInterfaceMethodVO;
import com.wuzeyu.gateway.center.domain.register.model.vo.ApplicationInterfaceVO;
import com.wuzeyu.gateway.center.domain.register.model.vo.ApplicationSystemVO;

/**
 * @author wuzeyu
 * @description api注册仓储
 */
public interface IRegisterManageRepository {

    void registerApplication(ApplicationSystemVO applicationSystemVO);

    void registerApplicationInterface(ApplicationInterfaceVO applicationInterfaceVO);

    void registerApplicationInterfaceMethod(ApplicationInterfaceMethodVO applicationInterfaceMethodVO);

}
