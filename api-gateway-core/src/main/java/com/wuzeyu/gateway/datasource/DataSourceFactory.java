package com.wuzeyu.gateway.datasource;

import com.wuzeyu.gateway.session.Configuration;

/**
 * @author wuzeyu
 * @description 数据源工厂
 * @github github.com/kkkkendei
 */
public interface DataSourceFactory {

    void setProperties(Configuration configuration, String uri);

    Datasource getDataSource();

}
