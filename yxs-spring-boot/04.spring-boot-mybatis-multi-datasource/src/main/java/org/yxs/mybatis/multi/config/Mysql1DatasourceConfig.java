package org.yxs.mybatis.multi.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @Description: mysql1 数据源配置
 * @Author: yang-xiansen
 * @Date: 2020/08/17 11:08
 */
@Configuration
@MapperScan(basePackages = Mysql1DatasourceConfig.PACKAGE, sqlSessionFactoryRef = "mysql1SqlSessionFactory")
public class Mysql1DatasourceConfig {

    // mysql扫描路径
    static final String PACKAGE = "org.yxs.mybatis.multi.mapper.mysql1";
    // mybatis mapper扫描路径
    static final String MAPPER_LOCATION = "classpath*:org/yxs/**/mapper/mysql1/*Mapper.xml";

    //    @Primary
    @Bean(name = "mysql1datasource")
    @ConfigurationProperties("spring.datasource.druid.mysql1")
    public DataSource mysql1DataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "mysql1TransactionManager")
//    @Primary
    public DataSourceTransactionManager mysql1TransactionManager() {
        return new DataSourceTransactionManager(mysql1DataSource());
    }

    @Bean(name = "mysql1SqlSessionFactory")
//    @Primary
    public SqlSessionFactory mysql1SqlSessionFactory(@Qualifier("mysql1datasource") DataSource dataSource)
        throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        //如果不使用xml的方式配置mapper，则可以省去下面这行mapper location的配置。
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
            .getResources(Mysql1DatasourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
