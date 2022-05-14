package com.example.funddbservice.config;

import com.example.funddbservice.service.FundDbService;
import com.example.funddbservice.service.impl.FundDbServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    FundDbService fundDbService() {
        return new FundDbServiceImpl();
    }

}
