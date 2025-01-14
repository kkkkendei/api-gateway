package com.wuzeyu.gateway.center.infrastructure.dao;


import com.wuzeyu.gateway.center.infrastructure.po.HttpStatement;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wuzeyu
 * @description 网关接口映射信息表DAO
 * @github github.com/kkkkendei
 */
@Mapper
public interface IHttpStatementDAO {

    List<HttpStatement> queryHttpStatementList();

}
