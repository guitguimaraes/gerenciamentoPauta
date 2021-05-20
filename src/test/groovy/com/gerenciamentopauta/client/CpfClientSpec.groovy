package com.gerenciamentopauta.client

import com.gerenciamentopauta.configuration.ConfiguracaoExternaUrl
import com.gerenciamentopauta.dto.ElegivelVotoDto
import com.gerenciamentopauta.exception.CpfException
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

class CpfClientSpec extends Specification {

    ConfiguracaoExternaUrl configuracaoExternaUrl = Mock(ConfiguracaoExternaUrl)
    RestTemplate restTemplate = Mock(RestTemplate)
    CpfClient cpfClient = new CpfClientImpl(configuracaoExternaUrl, restTemplate)


    def 'validando cpf sem erros'() {
        given:
        ResponseEntity<ElegivelVotoDto> responseEntity = Mock(ResponseEntity)
        1 * configuracaoExternaUrl.getCpfUrl() >> 'url'
        1 * restTemplate.getForEntity(_,_) >> responseEntity

        when:

        cpfClient.isCpfValid('cpf')

        then:

        noExceptionThrown()
    }

    def 'validando cpf com erro'() {
        given:
        1 * configuracaoExternaUrl.getCpfUrl() >> 'url'
        1 * restTemplate.getForEntity(*_) >> {throw new RestClientException('restError')}

        when:

        cpfClient.isCpfValid('cpf')

        then:
        thrown(CpfException)
    }
}
