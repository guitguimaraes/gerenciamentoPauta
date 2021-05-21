package com.gerenciamentopauta.mapper

import com.gerenciamentopauta.dto.PautaDto
import com.gerenciamentopauta.entity.Pauta
import spock.lang.Specification

class PautaMapperSpec extends Specification {

    def 'pautaMapper retotna pauta recebendo um DTO'() {
        given:

        PautaDto pautaDto = criaPautaDto()

        when:

        Pauta pauta = PautaMapper.mapPauta(pautaDto)

        then:

        pauta.nome == pautaDto.nome
        noExceptionThrown()
    }

    def 'pautaMapper retorno pautaDto recebendo uma pauta'() {
        given:

        Pauta pauta = criaPauta()

        when:

        PautaDto pautaDto = PautaMapper.mapPautaDto(pauta)

        then:

        pauta.nome == pautaDto.nome
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
        pautaDto.nome = 'nome'
        pautaDto
    }
}
