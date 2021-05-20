package com.gerenciamentopauta.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Dto de manipulação da pauta.
 */
@Data
public class PautaDto {
    private String pautaId;
    @NotNull(message = "Não pode ser Null")
    private String nome;
    private String descricao;
}
