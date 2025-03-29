package com.unicorn.store.configuration;

import org.apache.camel.CamelContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.unicorn.store.service.MyBean;

@Configuration
public class UnicornConfiguration {

    @Bean(name = "myBean")
    @ConditionalOnProperty(prefix = "camel.component.cenas", name = "enabled", havingValue = "true")
    public MyBean myBean() {
        return new MyBean();
    }

    
}
