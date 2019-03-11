package com.DemoDynamicJasper.spring.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

@Configuration
@ComponentScan("com")
public class SpringConfigurationBootstrap
{
    private static final Logger LOGGER = Logger.getLogger(SpringConfigurationBootstrap.class.getName());

    private static AnnotationConfigApplicationContext applicationContext;

    public static void initialize(Class clazz)
    {
        applicationContext = new AnnotationConfigApplicationContext(clazz);
        LOGGER.info("Spring application context initialized.");
    }

    public static AnnotationConfigApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
