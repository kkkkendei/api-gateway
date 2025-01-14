package com.wuzeyu.gateway.core.session.defaults;

import com.wuzeyu.gateway.core.datasource.DataSourceFactory;
import com.wuzeyu.gateway.core.datasource.Datasource;
import com.wuzeyu.gateway.core.datasource.unpooled.UnpooledDataSourceFactory;
import com.wuzeyu.gateway.core.session.GatewaySession;
import com.wuzeyu.gateway.core.executor.Executor;
import com.wuzeyu.gateway.core.session.Configuration;
import com.wuzeyu.gateway.core.session.GatewaySessionFactory;

/**
 * @author wuzeyu
 * @description 泛化调用会话工厂
 * @github github.com/kkkkendei
 */
public class DefaultGatewaySessionFactory implements GatewaySessionFactory {

    //private final Logger LOG = LoggerFactory.getLogger(DefaultGatewaySessionFactory.class);

    private final Configuration configuration;

    public DefaultGatewaySessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public GatewaySession openSession(String uri) {

        //获取数据源连接信息
        DataSourceFactory dataSourceFactory = new UnpooledDataSourceFactory();
        dataSourceFactory.setProperties(configuration, uri);
        Datasource datasource = dataSourceFactory.getDataSource();
        //创建执行器
        Executor executor = configuration.newExecutor(datasource.getConnection());

        //创建会话
        return new DefaultGatewaySession(configuration, uri, executor);
    }

   /* @Override
    public Future<Channel> openSession() throws ExecutionException, InterruptedException {

        GatewaySocketServer server = new GatewaySocketServer(configuration);

        Future<Channel> future = Executors.newFixedThreadPool(2).submit(server);
        Channel channel = future.get();

        if (channel == null) throw new RuntimeException("netty server start error channel is null");

        while (!channel.isActive()) {
            LOG.info("netty server gateway start Ing ...");
            Thread.sleep(500);
        }
        LOG.info("netty server gateway start Done! {}", channel.localAddress());

        return future;
    }*/
}
