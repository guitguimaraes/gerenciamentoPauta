package com.gerenciamentopauta.services;

import com.gerenciamentopauta.exception.NotFoundException;
import com.gerenciamentopauta.repository.PautaRepository;
import com.gerenciamentopauta.dto.PautaDto;
import com.gerenciamentopauta.entity.Pauta;
import com.gerenciamentopauta.mapper.PautaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementação dos serviços relacionados a pauta.
 */
@Service
public class PautaServiceImpl implements PautaService {

    @Autowired
    private PautaRepository pautaRepository;

    @Override
    public List<Pauta> getPautas() {
        return this.pautaRepository.findAll();
    }

    @Override
    public Pauta getPauta(String pautaId) {
        return this.pautaRepository.findById(pautaId)
                .orElseThrow(() -> new NotFoundException("pauta não encontrada"));
    }

    @Override
    public Pauta createPauta(PautaDto pautaDto) {
        return this.pautaRepository.save(PautaMapper.mapPauta(pautaDto));
    }

}
