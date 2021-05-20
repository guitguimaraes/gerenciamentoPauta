package com.gerenciamentopauta.services;

import com.gerenciamentopauta.exception.NotFoundException;
import com.gerenciamentopauta.exception.SessaoAbertaException;
import com.gerenciamentopauta.repository.SessaoRepository;
import com.gerenciamentopauta.repository.VotoRepository;
import com.gerenciamentopauta.dto.ResultadoVotacaoDto;
import com.gerenciamentopauta.dto.SessaoDto;
import com.gerenciamentopauta.entity.Sessao;
import com.gerenciamentopauta.entity.Voto;
import com.gerenciamentopauta.mapper.SessaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementação do servico relacionados a sessão.
 */
@Service
public class SessaoServiceimpl implements SessaoService {

    @Autowired
    private SessaoRepository sessaoRepository;

    @Autowired
    private VotoRepository votoRepository;

    @Override
    public List<Sessao> getSession() {
        return this.sessaoRepository.findAll();
    }

    @Override
    public Sessao getSessaoByPautaId(String pautaId) {
        return this.sessaoRepository.findByPautaId(pautaId)
                .orElseThrow(() -> new NotFoundException("Sessão não encontrada"));
    }

    @Override
    public Sessao criarSessao(SessaoDto sessaoDto) {
        if (sessaoRepository.findByPautaId(sessaoDto.getPautaId()).isPresent()) {
            throw new SessaoAbertaException("Sessão já aberta para esta pauta");
        }
        return this.sessaoRepository.insert(SessaoMapper.mapSessao(sessaoDto));
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
