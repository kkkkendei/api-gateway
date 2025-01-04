package com.wuzeyu.gateway.datasource.unpooled;

import com.wuzeyu.gateway.datasource.DataSourceFactory;
import com.wuzeyu.gateway.datasource.DataSourceType;
import com.wuzeyu.gateway.datasource.Datasource;
import com.wuzeyu.gateway.session.Configuration;

/**
 * @author wuzeyu
 * @description 数据源工厂实现类
 * @github github.com/kkkkendei
 */
public class UnpooledDataSourceFactory implements DataSourceFactory {

    protected UnpooledDataSource dataSource;

    public UnpooledDataSourceFactory() {this.dataSource = new UnpooledDataSource();}

    @Override
    public void setProperties(Configuration configuration, String uri) {
        this.dataSource.setConfiguration(configuration);
        this.dataSource.setDataSourceType(DataSourceType.Dubbo);
        this.dataSource.setHttpStatement(configuration.getHttpStatement(uri));
    }

    @Override
    public Datasource getDataSource() {
        return dataSource;
    }
}
