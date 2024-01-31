package com.hyundai.app.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

//@Configuration
//@MapperScan(value = "com.hyundai.app.mapper")
//@PropertySource("classpath:application.properties")
public class RootContextConfig {

//    @Value("${database.username}")
//    private String username;
//
//    @Value("${database.password}")
//    private String password;
//
//    @Value("${database.url}")
//    private String url;

//    @Bean
//    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
//        return new SqlSessionTemplate(sqlSessionFactory(dataSource()));
//    }

//    @Bean
//    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
//        sessionFactoryBean.setDataSource(dataSource);
//        sessionFactoryBean.setTypeHandlersPackage("com.livarter.app.config.DatabaseEnumTypeHandler");
//
//        Resource[] resources = new PathMatchingResourcePatternResolver()
//                .getResources("classpath:mapper/*.xml");
//        sessionFactoryBean.setMapperLocations(resources);
//        return sessionFactoryBean.getObject();
//    }

//    @Bean
//    public PlatformTransactionManager transactionManager(DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }

//    @Bean
//    public BasicDataSource dataSource() {
//        BasicDataSource basicDataSource = new BasicDataSource();
//        basicDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
//        basicDataSource.setUrl(url);
//        basicDataSource.setUsername(username);
//        basicDataSource.setPassword(password);
//        return basicDataSource;
//    }
}