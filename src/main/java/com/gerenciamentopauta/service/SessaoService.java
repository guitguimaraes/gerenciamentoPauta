package com.gerenciamentopauta.service;

import com.gerenciamentopauta.dto.ResultadoVotacaoDto;
import com.gerenciamentopauta.entity.Sessao;

import java.util.List;

/**
 * Interface dos serviços relacionados a sessão.
 */
public interface SessaoService {
    List<Sessao> obterSessoes();

    Sessao obterSessaoPelaPautaId(String pautaId);

    Sessao criarSessao(Sessao sessao);

    ResultadoVotacaoDto obtemResultadoSessaoPelaPautaId(String pautaId);
}
