package com.gerenciamentopauta.client;

import com.gerenciamentopauta.configuration.ConfiguracaoExternaUrl;
import com.gerenciamentopauta.dto.ElegivelVotoDto;
import com.gerenciamentopauta.exception.CpfException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static net.logstash.logback.argument.StructuredArguments.kv;

/**
 * implementação Client validação CPF.
 */
@Service
@Slf4j
@AllArgsConstructor
public class CpfClientImpl implements CpfClient {

    private final ConfiguracaoExternaUrl configuracaoExternaUrl;
    private final RestTemplate restTemplate;

    /**
     * serviço para validar cpf.
     *
     * @param cpf cpf da pessao validada.
     * @return ElegivelVotoDto informação se o cpf está apto a votar.
     */
    @Override
    public ResponseEntity<ElegivelVotoDto> isCpfValid(String cpf) {
        try {
            final ResponseEntity<ElegivelVotoDto> responseEntity = restTemplate
                .getForEntity(configuracaoExternaUrl.getCpfUrl().concat(cpf), ElegivelVotoDto.class);

            log.info("Validacao : {}, retorno do servico {}",
                kv("CPF ", cpf),
                kv("Response", responseEntity));

            return responseEntity;
        } catch (RestClientException exception) {
            log.error("Error validando cpf externamente", exception);
            throw new CpfException("CPF Inválido");
        }
    }
}
