package com.numarics.game.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DevelopmentClientConfiguration {

    @Bean
    public RestTemplate create() {
        return new RestTemplate();
    }
}
