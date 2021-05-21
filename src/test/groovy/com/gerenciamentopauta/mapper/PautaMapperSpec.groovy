package com.gerenciamentopauta.mapper

import com.gerenciamentopauta.dto.PautaRequestDto
import com.gerenciamentopauta.dto.PautaResponseDto
import com.gerenciamentopauta.entity.Pauta
import spock.lang.Specification

class PautaMapperSpec extends Specification {

    def 'pautaMapper retotna pauta recebendo um DTO'() {
        given:

        PautaRequestDto pautaDto = criaPautaDto()

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

        PautaResponseDto pautaDto = PautaMapper.mapPautaResponseDto(pauta)

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
        PautaRequestDto pautaDto = new PautaRequestDto()
        pautaDto.nome = 'nome'
        pautaDto
    }
}
