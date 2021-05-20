package com.gerenciamentopauta.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Dto de manipualação do voto.
 */
@Data
public class VotoDto {
    @NotNull(message = "Não pode ser Null")
    private String pautaId;
    @NotNull(message = "Não pode ser Null")
    private String voto;
    @NotNull(message = "Não pode ser Null")
    private String cpf;
}
