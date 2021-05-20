package com.gerenciamentopauta.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Entidade pauta.
 */
@Document
@Data
@Builder
public class Pauta {

    @Id
    private String pautaId;

    private String nome;

    private String descricao;
}
