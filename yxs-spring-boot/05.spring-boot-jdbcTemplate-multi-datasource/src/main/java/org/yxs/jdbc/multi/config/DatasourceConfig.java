package org.yxs.jdbc.multi.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @Description: mysql 数据源配置
 * @Author: yang-xiansen
 * @Date: 2020/08/17 11:08
 */
@Configuration
public class DatasourceConfig {

    @Primary //@Primary标志这个Bean如果在多个同类Bean候选时，该Bean优先被考虑。多数据源配置的时候，必须要有一个主数据源，用@Primary标志该Bean。
    @Bean(name = "mysqldatasource")
    @ConfigurationProperties("spring.datasource.druid.mysql")
    public DataSource mysqlDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "mysql1datasource")
    @ConfigurationProperties("spring.datasource.druid.mysql1")
    public DataSource mysql1DataSource() {
        return DruidDataSourceBuilder.create().build();
    }

//    @Bean(name = "oracledatasource")
//    @ConfigurationProperties("spring.datasource.druid.oracle")
//    public DataSource oracleDataSource() {
//        return DruidDataSourceBuilder.create().build();
//    }

    //    @Bean(name = "oracleJdbcTemplate")
//    public JdbcTemplate oracleJdbcTemplate(@Qualifier("oracledatasource") DataSource dataSource)
//        throws Exception {
//        return new JdbcTemplate(dataSource);
//    }

    @Bean(name = "mysqlTransactionManager")
    @Primary
    public DataSourceTransactionManager mysqlTransactionManager() {
        return new DataSourceTransactionManager(mysqlDataSource());
    }

    @Bean(name = "mysqlJdbcTemplate")
    public JdbcTemplate mysqlJdbcTemplate(@Qualifier("mysqldatasource") DataSource dataSource)
        throws Exception {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "mysql1JdbcTemplate")
    public JdbcTemplate mysql1JdbcTemplate(@Qualifier("mysql1datasource") DataSource dataSource)
        throws Exception {
        return new JdbcTemplate(dataSource);
    }


}
