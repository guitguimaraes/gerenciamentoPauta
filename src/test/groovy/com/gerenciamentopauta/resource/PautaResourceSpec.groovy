package com.gerenciamentopauta.resource

import com.gerenciamentopauta.dto.PautaDto
import com.gerenciamentopauta.entity.Pauta
import com.gerenciamentopauta.service.PautaService
import spock.lang.Specification

class PautaResourceSpec extends Specification {

    PautaService pautaService = Mock(PautaService)
    PautaResource pautaResource = new PautaResource(pautaService)

    def 'Obtem todas pautas e retorna lista de PautasDto'() {
        given:

        def pautasLista = [criaPauta(), criaPauta()]

        1 * pautaService.obterPautas() >> pautasLista

        when:
        List<PautaDto> pautasDtos = pautaResource.obterPautas()

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
        PautaDto pautaDto = pautaResource.obterPautaPeloPautaId('pautaId')

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

        PautaDto pautaDto = pautaResource.criarPauta(criaPautaDto())

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
        PautaDto pautaDto = new PautaDto()
        pautaDto.pautaId = 'pautaId'
        pautaDto
    }
}
