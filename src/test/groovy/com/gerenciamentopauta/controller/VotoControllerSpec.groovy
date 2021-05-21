package com.gerenciamentopauta.controller

import com.gerenciamentopauta.dto.VotoDto
import com.gerenciamentopauta.entity.Voto
import com.gerenciamentopauta.mapper.VotoMapper
import com.gerenciamentopauta.service.VotoService
import spock.lang.Specification

class VotoControllerSpec extends Specification {

    VotoService votoService = Mock(VotoService)
    VotoMapper votoMapper = Mock(VotoMapper)
    VotoController votoResource = new VotoController(votoService)

    def 'realiza voto com sucesso e retorno do Dto'() {
        given:
        VotoDto votoDto = CriaVotoDto()
        Voto voto = CriaVoto()

        1 * votoService.realizarVotacao(_) >> voto

        when:
        VotoDto votoDtoSalvo = votoResource.realizarVoto(votoDto)

        then:
        votoDto == votoDtoSalvo
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
