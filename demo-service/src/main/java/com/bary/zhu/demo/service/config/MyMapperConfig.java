package com.bary.zhu.demo.service.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@MapperScan(basePackages = MyMapperConfig.PACKAGE, sqlSessionFactoryRef = MyMapperConfig.NAME
        + "SessionFactory")
public class MyMapperConfig extends AbstractMapperConfig {
    static final String NAME = "my";
    static final String PACKAGE = "com.bary.zhu.demo.service.mapper";
    private static final String TYPE_ALIASES_PACKAGE = "com.bary.zhu.demo.service.domain";
    private static final String LOCATION_PATTERN = "classpath*:/**/*Mapper.xml";

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    @Bean(name = NAME + "DataSource")
    public DataSource addDataSource() {
        return getDataSource();
    }

    @Bean(name = NAME + "SessionFactory")
    public SqlSessionFactory addSqlSessionFactory(@Qualifier(NAME + "DataSource") DataSource dataSource,
            @Qualifier("mybatisConf") Resource resource) throws Exception {
        return getSqlSessionFactoryBean(dataSource, LOCATION_PATTERN, resource, TYPE_ALIASES_PACKAGE);
    }

    @Bean(name = NAME + "DataSourceTM")
    public DataSourceTransactionManager addDataSourceTransactionManager(
            @Qualifier(NAME + "DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    protected DataSource getDataSource() {
        DruidDataSource druidDataSource = DruidDataSourceBuilder.create().build();
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setDriverClassName(driver);
        return druidDataSource;
    }
}
