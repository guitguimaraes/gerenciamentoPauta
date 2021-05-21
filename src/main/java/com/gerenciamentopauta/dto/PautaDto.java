package com.gerenciamentopauta.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Dto de manipulação da pauta.
 */
@Data
public class PautaDto {

    @ApiModelProperty(value = "Pauta Id")
    private String pautaId;

    @ApiModelProperty(value = "Nome")
    @NotNull(message = "Não pode ser Null")
    private String nome;

    @ApiModelProperty(value = "Descrição")
    private String descricao;
}
