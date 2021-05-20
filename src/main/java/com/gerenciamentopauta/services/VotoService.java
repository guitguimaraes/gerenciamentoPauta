package com.gerenciamentopauta.services;

import com.gerenciamentopauta.dto.VotoDto;
import com.gerenciamentopauta.entity.Voto;

/**
 * Interface dos serviços relacionados a voto.
 */
public interface VotoService {
    Voto realizarVotacao(VotoDto votoDto);
}
