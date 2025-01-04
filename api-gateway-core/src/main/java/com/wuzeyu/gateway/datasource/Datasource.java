package com.wuzeyu.gateway.datasource;

/**
 * @author wuzeyu
 * @description 数据源接口，RPC、HTTP 都当作连接的数据资源使用
 * @github github.com/kkkkendei
 */
public interface Datasource {

    Connection getConnection();

}
