package com.gerenciamentopauta.service;

import com.gerenciamentopauta.dto.ResultadoVotacaoDto;
import com.gerenciamentopauta.entity.Sessao;
import com.gerenciamentopauta.entity.Voto;
import com.gerenciamentopauta.exception.NotFoundException;
import com.gerenciamentopauta.exception.SessaoAbertaException;
import com.gerenciamentopauta.publisher.GerenciamentoPautaPublisher;
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
    private GerenciamentoPautaPublisher gerenciamentoPautaPublisher;

    @Override
    public List<Sessao> obterSessoes() {
        return this.sessaoRepository.findAll();
    }

    @Override
    public Sessao obterSessaoPelaPautaId(String pautaId) {
        return this.sessaoRepository.findByPautaId(pautaId)
            .orElseThrow(() -> new NotFoundException("Sessao nao encontrada"));
    }

    @Override
    public Sessao criarSessao(Sessao sessao) {
        if (sessaoRepository.findByPautaId(sessao.getPautaId()).isPresent()) {
            throw new SessaoAbertaException("Sessão ja aberta para esta pauta");
        }
        Sessao sessaoCriada = this.sessaoRepository.insert(sessao);
        gerenciamentoPautaPublisher.publicarSessao(sessaoCriada);
        return sessaoCriada;
    }

    @Override
    public ResultadoVotacaoDto obtemResultadoSessaoPelaPautaId(String pautaId) {
        List<Voto> listaVoto = votoRepository.findByPautaId(pautaId)
            .orElseThrow(() -> new NotFoundException("Votos nao encontrados para a sessao"));

        ResultadoVotacaoDto resultadoVotacaoDto = new ResultadoVotacaoDto();
        resultadoVotacaoDto.setPautaId(pautaId);
        resultadoVotacaoDto.setVotoResultadoLista(
            listaVoto.stream().collect(
                Collectors.groupingBy(
                    Voto::getVoto, Collectors.counting()
                )
            ));
        gerenciamentoPautaPublisher.publicarResultado(resultadoVotacaoDto);
        return resultadoVotacaoDto;
    }

}
