package com.alex.consent.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://customerconsent_db_1:3306/test_db?serverTimezone=UTC&useSSL=false");
        dataSourceBuilder.username("mysql");
        dataSourceBuilder.password("mysql");
        return dataSourceBuilder.build();
    }
}
