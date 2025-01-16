package com.wuzeyu.gateway.center.infrastructure.dao;

import com.wuzeyu.gateway.center.infrastructure.po.ApplicationInterfaceMethod;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wuzeyu
 * @description 应用接口方法dao
 * @github github.com/kkkkendei
 */
@Mapper
public interface IApplicationInterfaceMethodDao {

    void insert(ApplicationInterfaceMethod applicationInterfaceMethod);

}
