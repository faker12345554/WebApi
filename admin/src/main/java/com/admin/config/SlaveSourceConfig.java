package com.admin.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

//@Configuration
//@MapperScan(basePackages = "com.admin.admin.dao.slave",sqlSessionFactoryRef = "slaveSqlSessionFactory")
public class SlaveSourceConfig {
//    @Bean(name = "slaveDataSource")
//    @ConfigurationProperties("spring.datasource.slave")
//    public DataSource slaveDataSource(){
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "slaveSqlSessionFactory")
//    public SqlSessionFactory sqlSessionFactory(@Qualifier("slaveDataSource") DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
//        sessionFactoryBean.setDataSource(dataSource);
//        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
//                .getResources("classpath*:mapper/slave/*.xml"));
//        return sessionFactoryBean.getObject();
//    }

}
