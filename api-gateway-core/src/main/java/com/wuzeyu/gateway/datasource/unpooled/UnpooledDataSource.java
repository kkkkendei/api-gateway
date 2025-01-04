package com.wuzeyu.gateway.datasource.unpooled;


import com.wuzeyu.gateway.datasource.Connection;
import com.wuzeyu.gateway.datasource.DataSourceType;
import com.wuzeyu.gateway.datasource.Datasource;
import com.wuzeyu.gateway.datasource.connection.DubboConnection;
import com.wuzeyu.gateway.mapping.HttpStatement;
import com.wuzeyu.gateway.session.Configuration;
import javafx.application.Application;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;


/**
 * @author wuzeyu
 * @description 无池化的连接池
 * @github github.com/kkkkendei
 */
public class UnpooledDataSource implements Datasource {

    private Configuration configuration;

    private HttpStatement httpStatement;

    private DataSourceType dataSourceType;


    @Override
    public Connection getConnection() {

        switch (dataSourceType) {
            case HTTP:
                // TODO
                break;
            case Dubbo:
                //配置信息
                String application = httpStatement.getApplication();
                String interfaceName = httpStatement.getInterfaceName();
                //获取服务
                ApplicationConfig applicationConfig = configuration.getApplicationConfig(application);
                RegistryConfig registryConfig = configuration.getRegistryConfig(application);
                ReferenceConfig<GenericService> reference = configuration.getReferenceConfig(interfaceName);

                return new DubboConnection(applicationConfig, registryConfig, reference);
            default: break;
        }

        throw new RuntimeException("DataSourceType: " + dataSourceType + "没有对应的数据源实现");

    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public void setHttpStatement(HttpStatement httpStatement) {
        this.httpStatement = httpStatement;
    }

    public void setDataSourceType(DataSourceType dataSourceType) {
        this.dataSourceType = dataSourceType;
    }
}
