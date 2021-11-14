package com.company.lifecycle.configuration;

import com.company.lifecycle.manager.LibraryManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    LibraryManager libraryManager(){
        return new LibraryManager();
    }

    @Bean
    BeanMonitor beanMonitor(){
        return new BeanMonitor();
    }
}
