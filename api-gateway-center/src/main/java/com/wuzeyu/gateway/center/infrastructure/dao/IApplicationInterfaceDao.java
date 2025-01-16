package com.wuzeyu.gateway.center.infrastructure.dao;


import com.wuzeyu.gateway.center.infrastructure.po.ApplicationInterface;
import com.wuzeyu.gateway.center.infrastructure.po.ApplicationSystem;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wuzeyu
 * @description 应用接口dao
 * @github github.com/kkkkendei
 */
@Mapper
public interface IApplicationInterfaceDao {

    void insert(ApplicationInterface applicationInterface);

}
