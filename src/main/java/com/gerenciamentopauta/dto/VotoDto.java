package com.gerenciamentopauta.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Dto de manipualação do voto.
 */
@Data
public class VotoDto {
    @ApiModelProperty(value = "Pauta Id")
    @NotNull(message = "Não pode ser Null")
    private String pautaId;

    @ApiModelProperty(value = "Valor do Voto", example = "SIM", allowableValues = "SIM, NAO")
    @NotNull(message = "Não pode ser Null")
    private String voto;

    @ApiModelProperty(value = "CPF")
    @NotNull(message = "Não pode ser Null")
    private String cpf;
}
