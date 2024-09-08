package com.santaBarbaraFs.contabilizarGols.repository;

import com.santaBarbaraFs.contabilizarGols.entites.Jogador;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JogadorRepository extends MongoRepository<Jogador, String> {

}
