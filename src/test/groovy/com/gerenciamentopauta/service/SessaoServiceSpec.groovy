package com.gerenciamentopauta.service

import com.gerenciamentopauta.dto.ResultadoVotacaoDto
import com.gerenciamentopauta.entity.Sessao
import com.gerenciamentopauta.entity.Voto
import com.gerenciamentopauta.exception.NotFoundException
import com.gerenciamentopauta.exception.SessaoAbertaException
import com.gerenciamentopauta.publisher.GerenciamentoPautaPublisher
import com.gerenciamentopauta.repository.SessaoRepository
import com.gerenciamentopauta.repository.VotoRepository
import spock.lang.Specification

class SessaoServiceSpec extends Specification {

    SessaoRepository sessaoRepository = Mock(SessaoRepository)
    VotoRepository votoRepository = Mock(VotoRepository)
    GerenciamentoPautaPublisher gerenciamentoPautaPublisher = Mock(GerenciamentoPautaPublisher)
    SessaoService sessaoService = new SessaoServiceimpl(sessaoRepository, votoRepository, gerenciamentoPautaPublisher)

    def 'Sessao deve ser adicionada sem erro'() {
        given: 'Sessao valida'

        Sessao sessaoInsert = Mock(Sessao)

        1 * sessaoRepository.findByPautaId(sessaoInsert.pautaId) >> Optional.empty()
        1 * sessaoRepository.insert(sessaoInsert) >> sessaoInsert

        when:

        Sessao sessao = sessaoService.criarSessao(sessaoInsert)

        then:

        sessao == sessaoInsert

        and:

        noExceptionThrown()

    }

    def 'Sessao nao deve ser adicionada erro SessaoAbertaException'() {
        given: 'Sessao ja aberta'

        Sessao sessaoInsert = Mock(Sessao)

        1 * sessaoRepository.findByPautaId(sessaoInsert.pautaId) >> Optional.of(sessaoInsert)

        when:

        sessaoService.criarSessao(sessaoInsert)

        then:

        thrown(SessaoAbertaException)

    }

    def 'Sessao deve ser retornada pela pautaId e sem erro'() {
        given: 'Pauta id'

        Sessao SessaoRetornada = Mock(Sessao)

        1 * sessaoRepository.findByPautaId(_) >> Optional.of(SessaoRetornada)

        when:

        Sessao sessao = sessaoService.obterSessaoPelaPautaId('id')

        then:

        sessao == SessaoRetornada

        and:

        noExceptionThrown()
    }

    def 'Sessão não encontrada deve lançar erro notFoundException'() {
        given: 'Pauta id'

        1 * sessaoRepository.findByPautaId(_) >> Optional.empty()

        when:

        sessaoService.obterSessaoPelaPautaId('id')

        then:

        thrown(NotFoundException)
    }

    def 'Todas sessao devem ser retornadas'() {
        given:

        def sessaoLista = [Mock(Sessao), Mock(Sessao)]

        1 * sessaoRepository.findAll() >> sessaoLista

        when:

        List<Sessao> sessoes = sessaoService.obterSessoes()

        then:
        sessoes.size() == sessaoLista.size()

        noExceptionThrown()
    }

    def 'Resultado deve ser retornado pela pautaId e sem erro'() {
        given: 'Pauta id'

        def sessaoLista = [criaVoto(), criaVoto()]

        votoRepository.findByPautaId('id') >> Optional.of(sessaoLista)

        when:

        ResultadoVotacaoDto resultadoVotacaoDto = sessaoService.obtemResultadoSessaoPelaPautaId('id')

        then:

        resultadoVotacaoDto.votoResultadoLista.get('SIM') == Long.valueOf(sessaoLista.size())

        and:

        noExceptionThrown()
    }

    def 'Resultado nao deve ser retornado erro notFoundException'() {
        given: 'Pauta id'

        votoRepository.findByPautaId('id') >> Optional.empty()

        when:

        sessaoService.obtemResultadoSessaoPelaPautaId('id')

        then:

        thrown(NotFoundException)
    }

    def criaVoto() {
        Voto voto = new Voto(
            'id',
            'pautaId',
            'SIM',
            '0289922222')
        voto
    }
}
