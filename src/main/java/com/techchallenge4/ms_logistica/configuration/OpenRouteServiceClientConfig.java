package com.techchallenge4.ms_logistica.configuration;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenRouteServiceClientConfig {

    @Value("${services.open-route-service.api-key}")
    private String apiKey;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> template.query("api-key", apiKey);
    }

}
