package com.gerenciamentopauta.service;

import com.gerenciamentopauta.dto.ResultadoVotacaoDto;
import com.gerenciamentopauta.entity.Sessao;
import com.gerenciamentopauta.entity.Voto;
import com.gerenciamentopauta.exception.NotFoundException;
import com.gerenciamentopauta.exception.SessaoAbertaException;
import com.gerenciamentopauta.repository.SessaoRepository;
import com.gerenciamentopauta.repository.VotoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementação do servico relacionados a sessão.
 */
@Service
@AllArgsConstructor
public class SessaoServiceimpl implements SessaoService {

    private SessaoRepository sessaoRepository;
    private VotoRepository votoRepository;

    @Override
    public List<Sessao> obterSessoes() {
        return this.sessaoRepository.findAll();
    }

    @Override
    public Sessao obterSessaoPelaPautaId(String pautaId) {
        return this.sessaoRepository.findByPautaId(pautaId)
            .orElseThrow(() -> new NotFoundException("Sessão não encontrada"));
    }

    @Override
    public Sessao criarSessao(Sessao sessao) {
        if (sessaoRepository.findByPautaId(sessao.getPautaId()).isPresent()) {
            throw new SessaoAbertaException("Sessão já aberta para esta pauta");
        }
        return this.sessaoRepository.insert(sessao);
    }

    @Override
    public ResultadoVotacaoDto obtemResultadoSessaoPelaPautaId(String pautaId) {
        List<Voto> listaVoto = votoRepository.findByPautaId(pautaId)
            .orElseThrow(() -> new NotFoundException("Votos não encontrados para a sessão"));

        ResultadoVotacaoDto resultadoVotacaoDto = new ResultadoVotacaoDto();
        resultadoVotacaoDto.setPautaId(pautaId);
        resultadoVotacaoDto.setVotoResultadoLista(
            listaVoto.stream().collect(
                Collectors.groupingBy(
                    Voto::getVoto, Collectors.counting()
                )
            ));

        return resultadoVotacaoDto;
    }

}
