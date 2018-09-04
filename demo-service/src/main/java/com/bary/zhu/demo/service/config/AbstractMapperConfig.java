package com.bary.zhu.demo.service.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
public abstract class AbstractMapperConfig {

    @Bean(name = "mybatisConf")
    @ConditionalOnMissingBean
    public Resource mybatisConf() {
        return new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml");
    }

    protected SqlSessionFactory getSqlSessionFactoryBean(DataSource dataSource, String locationPattern,
            Resource resource, String typeAliasesPackage) throws Exception {
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        Resource[] mapperLocations = new PathMatchingResourcePatternResolver().getResources(locationPattern);
        sqlSessionFactoryBean.setMapperLocations(mapperLocations);
        sqlSessionFactoryBean.setConfigLocation(resource);
        sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
        return sqlSessionFactoryBean.getObject();
    }
}
