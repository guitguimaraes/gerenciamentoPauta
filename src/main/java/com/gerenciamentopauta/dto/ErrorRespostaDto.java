package com.gerenciamentopauta.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Dto de retorno dos erros.
 */
@Data
@Builder
public class ErrorRespostaDto {
    private String mensagem;
}
