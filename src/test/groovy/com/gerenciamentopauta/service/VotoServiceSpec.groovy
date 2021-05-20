package com.gerenciamentopauta.service

import com.gerenciamentopauta.client.CpfClient
import com.gerenciamentopauta.dto.ElegivelVotoDto
import com.gerenciamentopauta.entity.Sessao
import com.gerenciamentopauta.entity.Voto
import com.gerenciamentopauta.exception.InelegivelVotarException
import com.gerenciamentopauta.exception.SessaoFechadaException
import com.gerenciamentopauta.exception.VotoExistenteException
import com.gerenciamentopauta.repository.VotoRepository
import org.springframework.http.ResponseEntity
import spock.lang.Specification

import java.time.LocalDateTime

class VotoServiceSpec extends Specification {

    SessaoService sessaoService = Mock(SessaoService)
    CpfClient cpfClient = Mock(CpfClient)
    VotoRepository votoRepository = Mock(VotoRepository)
    VotoService votoService = new VotoServiceImpl(sessaoService, cpfClient, votoRepository)

    def 'Voto deve ser adicionada sem erro'() {
        given: 'voto valido'

        ResponseEntity response = Mock(ResponseEntity)
        Voto voto = CreateVoto()
        Sessao sessao = createSessao()
        ElegivelVotoDto elegivel = CreateElegivelDto()

        sessaoService.obterSessaoPelaPautaId(_) >> sessao
        votoRepository.findByPautaIdAndCpfPessoa(_, _) >> Optional.empty()
        cpfClient.isCpfValid(voto.getCpf()) >>  response
        response.body >> elegivel
        votoRepository.insert(voto) >> voto

        when:

        Voto votoSalvo = votoService.realizarVotacao(voto);

        then:

        voto == votoSalvo

        and:

        noExceptionThrown()

    }


    def 'Voto não deve ser adicionada SessaoFechadaException deve ser lancado'() {
        given: 'voto valido'

        Voto voto = CreateVoto()
        Sessao sessao = createSessao()
        sessao.setDataFinal(LocalDateTime.now().minusMinutes(10))

        sessaoService.obterSessaoPelaPautaId(_) >> sessao
        when:

         votoService.realizarVotacao(voto);

        then:
        thrown(SessaoFechadaException)

    }

    def 'Voto já adicionado, VotoExistenteException lancada'() {
        given: 'voto valido'

        Voto voto = CreateVoto()
        Sessao sessao = createSessao()

        sessaoService.obterSessaoPelaPautaId(_) >> sessao
        votoRepository.findByPautaIdAndCpfPessoa(_, _) >> Optional.of(voto)

        when:

        votoService.realizarVotacao(voto);

        then:

        thrown(VotoExistenteException)

    }

    def 'Voto nao adicionada, cpf nao valido'() {
        given: 'voto valido'

        ResponseEntity response = Mock(ResponseEntity)
        Voto voto = CreateVoto()
        Sessao sessao = createSessao()
        ElegivelVotoDto elegivel = CreateElegivelDto()
        elegivel.status = 'UNABLE_TO_VOTE'

        sessaoService.obterSessaoPelaPautaId(_) >> sessao
        votoRepository.findByPautaIdAndCpfPessoa(_, _) >> Optional.empty()
        cpfClient.isCpfValid(voto.getCpf()) >>  response
        response.body >> elegivel

        when:

         votoService.realizarVotacao(voto);

        then:
        thrown(InelegivelVotarException)

    }

    def createSessao() {
        Sessao sessao = new Sessao(
            'id',
            'pautaId',
            LocalDateTime.now(),
            LocalDateTime.now().plusMinutes(100)
        )
        sessao
    }

    def CreateVoto() {
        Voto voto = new Voto(
            'id',
            'pautaId',
            'SIM',
            '40819169005')
        voto
    }

    def CreateElegivelDto(){
        ElegivelVotoDto elegivel = new ElegivelVotoDto()
        elegivel.status = 'ABLE_TO_VOTE'
        elegivel
    }
}
