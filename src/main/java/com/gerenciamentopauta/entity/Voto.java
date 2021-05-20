package com.gerenciamentopauta.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Entidade Voto.
 */
@Data
@Document
@Builder
public class Voto {
    @Id
    private String id;
    private String pautaId;
    private String voto;
    private String cpf;

}
