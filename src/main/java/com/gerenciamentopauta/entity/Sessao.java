package com.gerenciamentopauta.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * Entidade Sessao.
 */
@Data
@Builder
@Document
public class Sessao {
    @Id
    private String sessaoId;
    private String pautaId;
    private LocalDateTime dataInicial;
    private LocalDateTime dataFinal;
}

