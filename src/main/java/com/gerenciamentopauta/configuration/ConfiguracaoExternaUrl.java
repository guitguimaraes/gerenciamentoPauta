package com.gerenciamentopauta.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração das url externas da aplicação.
 */

@Configuration
@ConfigurationProperties(prefix = "externo")
@Data
public class ConfiguracaoExternaUrl {
    private String cpfUrl;
}
