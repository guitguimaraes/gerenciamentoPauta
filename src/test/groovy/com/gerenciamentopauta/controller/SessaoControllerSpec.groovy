package com.gerenciamentopauta.controller

import com.gerenciamentopauta.dto.ResultadoVotacaoDto
import com.gerenciamentopauta.dto.SessaoRequestDto
import com.gerenciamentopauta.dto.SessaoResponseDto
import com.gerenciamentopauta.entity.Sessao
import com.gerenciamentopauta.service.SessaoService
import spock.lang.Specification

import java.time.LocalDateTime

class SessaoControllerSpec extends Specification {

    SessaoService sessaoService = Mock(SessaoService)
    SessaoController sessaoResource = new SessaoController(sessaoService)

    def 'Obtem todas sessoes e retorna lista de SessoesDto'() {
        given:

        def sessaoLista = [criaSessao(), criaSessao()]

        1 * sessaoService.obterSessoes() >> sessaoLista

        when:
        List<SessaoResponseDto> sessoes = sessaoResource.obterSessoes()

        then:

        sessoes.size() == sessaoLista.size()

        and:

        noExceptionThrown()
    }

    def 'Obtem sessão por pautaId e retorna o dto da sessao encontrada'() {
        given:

        def sessao = criaSessao()

        1 * sessaoService.obterSessaoPelaPautaId(sessao.pautaId) >> sessao

        when:
        SessaoResponseDto sessaoDto = sessaoResource.obtemSessaoPorPautaId('pautaId')

        then:

        sessaoDto.pautaId == sessao.pautaId

        and:

        noExceptionThrown()
    }

    def 'Abre sessao para votacao e retorna o dto da sessao criada'() {
        given:

        def sessao = criaSessao()
        1 * sessaoService.criarSessao(_) >> sessao

        when:

        SessaoResponseDto sessaoDto = sessaoResource.abrirSessao(criaSessaoDto())

        then:

        sessaoDto.pautaId == sessao.pautaId

        and:

        noExceptionThrown()
    }

    def 'obtem resultado da votaçao da sessão  retornando o dto do resultado da sessao'() {
        given:

        def resultadoVotacaoDtoMock = criaResultadoVotacao()

        1 * sessaoService.obtemResultadoSessaoPelaPautaId(_) >> resultadoVotacaoDtoMock

        when:

        ResultadoVotacaoDto resultadoVotacaoDto = sessaoResource.obtemResultadoSessaoPelaPautaId('pautaId')

        then:

        resultadoVotacaoDtoMock.pautaId == resultadoVotacaoDto.pautaId

        and:

        noExceptionThrown()
    }

    def criaSessao() {
        Sessao sessao = new Sessao(
            'id',
            'pautaId',
            LocalDateTime.now(),
            LocalDateTime.now().plusMinutes(100)
        )
        sessao
    }

    def criaSessaoDto() {
        SessaoRequestDto sessaoDto = new SessaoRequestDto()
        sessaoDto.pautaId = 'pautaId'
        sessaoDto
    }

    def criaResultadoVotacao() {
        ResultadoVotacaoDto resultadoVotacaoDto = new ResultadoVotacaoDto()
        resultadoVotacaoDto.pautaId = 'pautaId'
        resultadoVotacaoDto.votoResultadoLista = new HashMap<>()
        resultadoVotacaoDto.votoResultadoLista['Sim'] = 2
        resultadoVotacaoDto
    }
}
