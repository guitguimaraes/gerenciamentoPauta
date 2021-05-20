package com.gerenciamentopauta.service;

import com.gerenciamentopauta.entity.Pauta;

import java.util.List;

/**
 * Interface dos serviços relacionados a pauta.
 */
public interface PautaService {

    List<Pauta> obterPautas();

    Pauta obterPauta(String pautaId);

    Pauta criarPauta(Pauta pauta);
}
