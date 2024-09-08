package com.santaBarbaraFs.contabilizarGols.repository;

import com.santaBarbaraFs.contabilizarGols.entites.Jogador;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JogadorRepository extends MongoRepository<Jogador, String> {
     List<Jogador> findByNome(String nome);
}
