package com.gerenciamentopauta.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Dto de manipualação da sessao.
 */
@Data
public class SessaoDto {
    @NotNull(message = "Não pode ser Null")
    private String pautaId;
    private long duracaoSessao = 1L;
    private LocalDateTime dataInicial;
    private LocalDateTime dataFinal;
}
