package com.gerenciamentopauta.repository;

import com.gerenciamentopauta.entity.Voto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio relacionados a voto.
 */
public interface VotoRepository extends MongoRepository<Voto, String> {

    @Query("{ 'pautaId': ?0, 'cpf': ?1}")
    Optional<Voto> findByPautaIdAndCpfPessoa(String idPauta, String cpfPessoa);

    @Query("{ 'pautaId': ?0}")
    Optional<List<Voto>> findByPautaId(String idPauta);
}
