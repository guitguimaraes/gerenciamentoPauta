package com.gerenciamentopauta.services;

import com.gerenciamentopauta.dto.PautaDto;
import com.gerenciamentopauta.entity.Pauta;

import java.util.List;

/**
 * Interface dos serviços relacionados a pauta.
 */
public interface PautaService {

    List<Pauta> getPautas();

    Pauta getPauta(String pautaId);

    Pauta createPauta(PautaDto pautaDto);
}
