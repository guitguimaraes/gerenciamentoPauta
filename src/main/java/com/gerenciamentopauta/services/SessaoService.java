package com.gerenciamentopauta.services;

import com.gerenciamentopauta.dto.ResultadoVotacaoDto;
import com.gerenciamentopauta.dto.SessaoDto;
import com.gerenciamentopauta.entity.Sessao;

import java.util.List;

/**
 * Interface dos serviços relacionados a sessão.
 */
public interface SessaoService {
    List<Sessao> getSession();

    Sessao getSessaoByPautaId(String pautaId);

    Sessao criarSessao(SessaoDto sessaoDto);

    ResultadoVotacaoDto obtemResultadoSessaoPelaPautaId(String pautaId);
}
