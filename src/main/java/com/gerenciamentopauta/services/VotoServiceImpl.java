package com.gerenciamentopauta.services;

import com.gerenciamentopauta.exception.InelegivelVotarException;
import com.gerenciamentopauta.exception.SessaoFechadaException;
import com.gerenciamentopauta.repository.VotoRepository;
import com.gerenciamentopauta.client.CpfClient;
import com.gerenciamentopauta.dto.ElegivelVotoDto;
import com.gerenciamentopauta.dto.VotoDto;
import com.gerenciamentopauta.entity.Sessao;
import com.gerenciamentopauta.entity.Voto;
import com.gerenciamentopauta.mapper.VotoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Implementação dos serviços relacionados ao voto.
 */
@RequiredArgsConstructor
@Service
public class VotoServiceImpl implements VotoService {

    private static final String ABLE_TO_VOTE = "ABLE_TO_VOTE";

    @Autowired
    SessaoService sessaoService;

    @Autowired
    CpfClient cpfClient;

    @Autowired
    VotoRepository votoRepository;

    @Override
    public Voto realizarVotacao(VotoDto votoDto) {
        if (!isSessaoAberta(votoDto.getPautaId())) {
            throw new SessaoFechadaException(String.format("A pauta %s não contem sessão aberta", votoDto.getPautaId()));
        }
        if (!isElegivelVotar(votoDto)) {
            throw new InelegivelVotarException(String.format("CPF %s, não está apto a realizar votação nessa pauta.", votoDto.getCpf()));
        }
        return votoRepository.insert(VotoMapper.mapVoto(votoDto));
    }

    private boolean isElegivelVotar(VotoDto votoDto) {

        final ElegivelVotoDto dto = cpfClient.isCpfValid(votoDto.getCpf()).getBody();

        final boolean pessoaVotou = pessoaVotou(votoDto.getPautaId(), votoDto.getCpf());

        return dto != null && ABLE_TO_VOTE.equals(dto.getStatus()) && !pessoaVotou;
    }

    private boolean isSessaoAberta(String pautaId) {

        final Sessao sessao = sessaoService.getSessaoByPautaId(pautaId);

        return sessao.getDataFinal().isAfter(LocalDateTime.now());
    }

    private boolean pessoaVotou(String pautaId, String cpf) {
        return votoRepository.findByPautaIdAndCpfPessoa(pautaId, cpf).isPresent();
    }

}
