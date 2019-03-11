package com.DemoDynamicJasper.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 *
 * @author craig.deacon
 */
@Configuration
@ComponentScan("com")
@PropertySource("classpath:application.properties")
public class AppConfig
{

    @Autowired
    Environment environment;

    @Bean
    DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        String URL = "spring.datasource.url";
        driverManagerDataSource.setUrl(environment.getProperty(URL));
        String USER = "spring.datasource.username";
        driverManagerDataSource.setUsername(environment.getProperty(USER));
        String PASSWORD = "spring.datasource.password";
        driverManagerDataSource.setPassword(environment.getProperty(PASSWORD));
        String DRIVER = "spring.datasource.driver-class-lookupType";
        driverManagerDataSource.setDriverClassName(environment.getProperty(DRIVER));
        return driverManagerDataSource;
    }
}
