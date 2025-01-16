package com.wuzeyu.gateway.center.infrastructure.dao;


import com.wuzeyu.gateway.center.infrastructure.po.ApplicationSystem;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wuzeyu
 * @description 应用dao
 * @github github.com/kkkkendei
 */
@Mapper
public interface IApplicationSystemDao {

    void insert(ApplicationSystem applicationSystem);

}