package com.gerenciamentopauta.mapper

import com.gerenciamentopauta.dto.VotoDto
import com.gerenciamentopauta.entity.Voto
import spock.lang.Specification

class VotoMapperSpec extends Specification {

    def 'votoMapper retotna voto recebendo um DTO'() {
        given:

        VotoDto votoDto = CriaVotoDto()

        when:

        Voto voto = VotoMapper.mapVoto(votoDto)

        then:

        voto.voto == votoDto.voto
        noExceptionThrown()
    }

    def 'votoMapper retorno votoDto recebendo uma voto'() {
        given:

        Voto voto = CriaVoto()

        when:

        VotoDto votoDto = VotoMapper.mapVotoDto(voto)

        then:

        voto.voto == votoDto.voto
        noExceptionThrown()
    }

    def CriaVotoDto() {
        VotoDto votoDto = new VotoDto()
        votoDto.pautaId = 'pautaId'
        votoDto.cpf = '40819169005'
        votoDto.voto = 'SIM'
        votoDto
    }

    def CriaVoto() {
        Voto voto = new Voto(
            'id',
            'pautaId',
            'SIM',
            '40819169005')
        voto
    }
}
