package com.gerenciamentopauta.repository;

import com.gerenciamentopauta.entity.Sessao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

/**
 * Repositorio relacionados a sessão.
 */
public interface SessaoRepository extends MongoRepository<Sessao, String> {

    @Query("{ 'pautaId': ?0}")
    Optional<Sessao> findByPautaId(String pautaId);
}
