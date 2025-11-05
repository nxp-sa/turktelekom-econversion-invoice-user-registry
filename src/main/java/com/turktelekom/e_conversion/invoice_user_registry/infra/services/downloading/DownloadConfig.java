package com.turktelekom.e_conversion.invoice_user_registry.infra.services.downloading;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DownloadConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
