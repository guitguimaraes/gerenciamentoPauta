package com.gerenciamentopauta.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Classe de implementcao do Rest Template.
 */
@Configuration
public class RestTemplateConfiguration {
    /**
     * Bean para implementação do RestTemplate.
     *
     * @return RestTemplate
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}


