package com.gerenciamentopauta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Ponto de inicio da applicação.
 */
@SpringBootApplication
public class GerenciamentoPautasApplication {

    /**
     * Bean para implementação do RestTemplate.
     *
     * @return RestTemplate
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * Ponto de inicio do serviço de SpringBoot.
     *
     * @param args String
     */
    public static void main(String[] args) {
        SpringApplication.run(GerenciamentoPautasApplication.class, args);
    }
}
