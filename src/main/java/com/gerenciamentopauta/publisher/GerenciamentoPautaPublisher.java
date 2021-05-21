package com.gerenciamentopauta.publisher;

import com.gerenciamentopauta.dto.ResultadoVotacaoDto;
import com.gerenciamentopauta.entity.Pauta;
import com.gerenciamentopauta.entity.Sessao;
import com.gerenciamentopauta.entity.Voto;

/**
 * Interface responsável por publicar eventos no kafka.
 */
public interface GerenciamentoPautaPublisher {

    void publicarVoto(Voto voto);

    void publicarPauta(Pauta pauta);

    void publicarSessao(Sessao sessao);

    void publicarResultado(ResultadoVotacaoDto resultado);
}
