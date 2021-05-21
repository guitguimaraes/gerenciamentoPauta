package com.gerenciamentopauta.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * Dto de retorno dos erros.
 */
@Data
@Builder
public class ErrorRespostaDto {
    @ApiModelProperty(value = "Mensagem", position = 1)
    private String mensagem;
}
