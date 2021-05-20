package com.gerenciamentopauta.client;

import com.gerenciamentopauta.dto.ElegivelVotoDto;
import org.springframework.http.ResponseEntity;

/**
 * Interface validação CPF.
 */
public interface CpfClient {
    ResponseEntity<ElegivelVotoDto> isCpfValid(String cpf);
}
