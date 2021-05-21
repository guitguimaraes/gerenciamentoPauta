package com.gerenciamentopauta.service;

import com.gerenciamentopauta.client.CpfClient;
import com.gerenciamentopauta.dto.ElegivelVotoDto;
import com.gerenciamentopauta.entity.Sessao;
import com.gerenciamentopauta.entity.Voto;
import com.gerenciamentopauta.exception.InelegivelVotarException;
import com.gerenciamentopauta.exception.SessaoFechadaException;
import com.gerenciamentopauta.exception.VotoExistenteException;
import com.gerenciamentopauta.publisher.GerenciamentoPautaPublisher;
import com.gerenciamentopauta.repository.VotoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Implementação dos serviços relacionados ao voto.
 */
@AllArgsConstructor
@Service
public class VotoServiceImpl implements VotoService {

    private static final String ABLE_TO_VOTE = "ABLE_TO_VOTE";

    private SessaoService sessaoService;
    private CpfClient cpfClient;
    private VotoRepository votoRepository;
    private GerenciamentoPautaPublisher gerenciamentoPautaPublisher;

    @Override
    public Voto realizarVotacao(Voto voto) {
        if (!isSessaoAberta(voto.getPautaId())) {
            throw new SessaoFechadaException(String.format("A pauta %s não contem sessao aberta", voto.getPautaId()));
        }
        if (!isElegivelVotar(voto)) {
            throw new InelegivelVotarException(String.format("CPF %s, não esta apto a realizar votacao nessa pauta.", voto.getCpf()));
        }
        Voto votoCriado = votoRepository.insert(voto);
        gerenciamentoPautaPublisher.publicarVoto(votoCriado);
        return votoCriado;
    }

    private boolean isSessaoAberta(String pautaId) {
        final Sessao sessao = sessaoService.obterSessaoPelaPautaId(pautaId);
        return sessao.getDataFinal().isAfter(LocalDateTime.now());
    }

    private boolean isElegivelVotar(Voto voto) {
        if (!pessoaVotou(voto.getPautaId(), voto.getCpf())) {
            final ElegivelVotoDto dto = cpfClient.isCpfValid(voto.getCpf()).getBody();
            return dto != null && ABLE_TO_VOTE.equals(dto.getStatus());
        }
        throw new VotoExistenteException(String.format("Voto com esse cpf %s, ja realizado", voto.getCpf()));
    }

    private boolean pessoaVotou(String pautaId, String cpf) {
        return votoRepository.findByPautaIdAndCpf(pautaId, cpf).isPresent();
    }

}
