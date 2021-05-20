package com.gerenciamentopauta.dto;

import lombok.Data;

import java.util.Map;

/**
 * Dto para informar o resultado de votação na sessao.
 */
@Data
public class ResultadoVotacaoDto {
    String pautaId;
    Map<String, Long> votoResultadoLista;

    public String getPautaId() {
        return pautaId;
    }

    public void setPautaId(String pautaId) {
        this.pautaId = pautaId;
    }
}
