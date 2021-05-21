package com.gerenciamentopauta.controller

import com.gerenciamentopauta.dto.PautaRequestDto
import com.gerenciamentopauta.dto.PautaResponseDto
import com.gerenciamentopauta.entity.Pauta
import com.gerenciamentopauta.service.PautaService
import spock.lang.Specification

class PautaControllerSpec extends Specification {

    PautaService pautaService = Mock(PautaService)
    PautaController pautaResource = new PautaController(pautaService)

    def 'Obtem todas pautas e retorna lista de PautasDto'() {
        given:

        def pautasLista = [criaPauta(), criaPauta()]

        1 * pautaService.obterPautas() >> pautasLista

        when:
        List<PautaResponseDto> pautasDtos = pautaResource.obterPautas()

        then:

        pautasDtos.size() == pautasLista.size()

        and:

        noExceptionThrown()
    }

    def 'Obtem pauta por pautaId e retorna o dto da pauta encontrada'() {
        given:

        def pauta = criaPauta()

        1 * pautaService.obterPauta(_) >> pauta

        when:
        PautaResponseDto pautaDto = pautaResource.obterPautaPeloPautaId('pautaId')

        then:

        pautaDto.pautaId == pauta.pautaId

        and:

        noExceptionThrown()
    }

    def 'cria pauta e retorna o dto da pauta criada'() {
        given:

        def pauta = criaPauta()

        1 * pautaService.criarPauta(_) >> pauta

        when:

        PautaResponseDto pautaDto = pautaResource.criarPauta(criaPautaDto())

        then:

        pautaDto.pautaId == pauta.pautaId

        and:

        noExceptionThrown()
    }

    def criaPauta() {
        Pauta pauta = new Pauta(
            'nome',
            'pautaIddescricao',
            'descricao'
        )
        pauta
    }

    def criaPautaDto() {
        PautaRequestDto pautaDto = new PautaRequestDto()
        pautaDto.nome = 'pautaId'
        pautaDto
    }
}
