package com.gerenciamentopauta.service;

import com.gerenciamentopauta.entity.Pauta;
import com.gerenciamentopauta.exception.NotFoundException;
import com.gerenciamentopauta.repository.PautaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementação dos serviços relacionados a pauta.
 */
@Service
@AllArgsConstructor
public class PautaServiceImpl implements PautaService {

    private PautaRepository pautaRepository;

    @Override
    public List<Pauta> obterPautas() {
        return this.pautaRepository.findAll();
    }

    @Override
    public Pauta obterPauta(String pautaId) {
        return this.pautaRepository.findById(pautaId)
            .orElseThrow(() -> new NotFoundException("pauta não encontrada"));
    }

    @Override
    public Pauta criarPauta(Pauta pauta) {
        return this.pautaRepository.insert(pauta);
    }

}
