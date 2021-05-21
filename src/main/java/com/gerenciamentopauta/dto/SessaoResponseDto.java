package com.gerenciamentopauta.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Dto de manipualação da sessao.
 */
@Data
public class SessaoResponseDto {
    @ApiModelProperty(value = "Pauta Id")
    @NotNull(message = "Não pode ser Null")
    private String pautaId;
    @ApiModelProperty(value = "Duração Sessão Votação")
    private long duracaoSessao = 1L;
    private LocalDateTime dataInicial;
    private LocalDateTime dataFinal;
}
