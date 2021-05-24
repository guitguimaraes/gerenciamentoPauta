package com.gerenciamentopauta.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Dto de manipualação da sessao.
 */
@Data
public class SessaoRequestDto {
    @ApiModelProperty(value = "Pauta Id")
    @NotNull(message = "Não pode ser Null")
    private String pautaId;
    @ApiModelProperty(value = "Duração Sessão Votação", example = "1")
    private long duracaoSessao = 1L;
}
