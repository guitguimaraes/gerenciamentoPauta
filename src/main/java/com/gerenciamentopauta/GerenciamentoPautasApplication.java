package com.gerenciamentopauta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Ponto de inicio da applicação.
 */
@SpringBootApplication
public class GerenciamentoPautasApplication {
    /**
     * Ponto de inicio do serviço de SpringBoot.
     *
     * @param args String
     */
    public static void main(String[] args) {
        SpringApplication.run(GerenciamentoPautasApplication.class, args);
    }
}
