package com.gerenciamentopauta.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dto de retorno do serviço de CPF.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ElegivelVotoDto {
    private String status;
}
