package com.gerenciamentopauta.service

import com.gerenciamentopauta.entity.Pauta
import com.gerenciamentopauta.exception.NotFoundException
import com.gerenciamentopauta.repository.PautaRepository
import spock.lang.Specification

class PautaServiceImplSpec extends Specification {

    PautaRepository pautaRepository = Mock(PautaRepository)
    PautaService pautaService = new PautaServiceImpl(pautaRepository)

    def 'Pauta deve ser adicionada sem erro'() {
        given: 'Pauta válida'

        Pauta pautaInsert = Mock(Pauta)

        1 * pautaRepository.insert(pautaInsert) >> pautaInsert

        when:

        Pauta pauta = pautaService.criarPauta(pautaInsert)

        then:

        pauta == pautaInsert

        and:

        noExceptionThrown()

    }

    def 'Pauta deve ser retornada pelo id e sem erro'() {
        given: 'Pauta id'

        Pauta pautaRetornada = Mock(Pauta)

        1 * pautaRepository.findById('id') >> Optional.of(pautaRetornada)

        when:

        Pauta pauta = pautaService.obterPauta('id')

        then:

        pauta == pautaRetornada

        and:

        noExceptionThrown()
    }

    def 'Pauta não encontrada deve lançar erro notFoundException'() {
        given: 'Pauta id'

        1 * pautaRepository.findById('id') >> Optional.empty()

        when:

        pautaService.obterPauta('id')

        then:

        thrown(NotFoundException)
    }

    def 'Todas Pautas devem ser retornadas'() {
        given: 'Pauta id'

        def pautaList = [Mock(Pauta), Mock(Pauta)]

        1 * pautaRepository.findAll() >> pautaList

        when:

        List<Pauta> pautas = pautaService.obterPautas()

        then:
        pautas.size() == pautaList.size()

        noExceptionThrown()
    }
}
