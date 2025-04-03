package com.unicorn.store.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.unicorn.store.service.MyBean;

@Configuration
public class UnicornConfiguration {

    @Bean(name = "myBean")
    @ConditionalOnProperty(prefix = "camel.component.cenas", name = "enabled", havingValue = "true")
    MyBean myBean() {
        return new MyBean();
    }

}
