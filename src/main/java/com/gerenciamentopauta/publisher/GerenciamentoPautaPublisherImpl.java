package com.gerenciamentopauta.publisher;

import com.gerenciamentopauta.dto.ResultadoVotacaoDto;
import com.gerenciamentopauta.entity.Pauta;
import com.gerenciamentopauta.entity.Sessao;
import com.gerenciamentopauta.entity.Voto;
import org.springframework.stereotype.Service;

/**
 * Implementação responsável por publicar eventos no kafka.
 */
@Service
public class GerenciamentoPautaPublisherImpl implements GerenciamentoPautaPublisher {
    @Override
    public void publicarVoto(Voto voto) {
        //[todo] implementação publicação kafka
    }

    @Override
    public void publicarPauta(Pauta pauta) {
        //[todo] implementação publicação kafka
    }

    @Override
    public void publicarSessao(Sessao sessao) {
        //[todo] implementação publicação kafka
    }

    @Override
    public void publicarResultado(ResultadoVotacaoDto resultado) {
        //[todo] implementação publicação kafka
    }
}
