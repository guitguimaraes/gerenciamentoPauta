package com.gerenciamentopauta.mapper

import com.gerenciamentopauta.dto.SessaoRequestDto
import com.gerenciamentopauta.dto.SessaoResponseDto
import com.gerenciamentopauta.entity.Sessao
import spock.lang.Specification

import java.time.LocalDateTime

class SessaoMapperSpec extends Specification {

    def 'SessaoMapper retotna sessao recebendo um DTO'() {
        given:

        SessaoRequestDto sessaoDto = criaSessaoDto()

        when:

        Sessao sessao = SessaoMapper.mapSessao(sessaoDto)

        then:

        sessao.pautaId == sessao.pautaId
        noExceptionThrown()
    }

    def 'SessaoMapper retorno sessaoDto recebendo uma sessao'() {
        given:

        Sessao sessao = criaSessao()

        when:

        SessaoResponseDto sessaoDto = SessaoMapper.mapSessaoResponseDto(sessao)

        then:

        sessao.pautaId == sessaoDto.pautaId
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
}
