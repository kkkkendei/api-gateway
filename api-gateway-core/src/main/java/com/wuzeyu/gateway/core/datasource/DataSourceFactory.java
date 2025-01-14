package com.wuzeyu.gateway.core.datasource;

import com.wuzeyu.gateway.core.session.Configuration;

/**
 * @author wuzeyu
 * @description 数据源工厂
 * @github github.com/kkkkendei
 */
public interface DataSourceFactory {

    void setProperties(Configuration configuration, String uri);

    Datasource getDataSource();

}
