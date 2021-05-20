package com.gerenciamentopauta.repository;

import com.gerenciamentopauta.entity.Pauta;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repositorio relacionados a pauta.
 */
public interface PautaRepository extends MongoRepository<Pauta, String> {
}
