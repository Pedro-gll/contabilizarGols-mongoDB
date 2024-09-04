package com.santaBarbaraFs.contabilizarGols.service;

import com.santaBarbaraFs.contabilizarGols.entites.Jogador;
import com.santaBarbaraFs.contabilizarGols.repository.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JogadorService {

    @Autowired
    JogadorRepository jogadorRepository;

    public List<Jogador> findAll(){
        return jogadorRepository.findAll();
    }

    public Jogador findById(String id){
        Optional<Jogador> jogador = jogadorRepository.findById(id);
        return jogador.orElseThrow(() -> new RuntimeException("Objeto não encontrado"));
    }

    public Jogador inserir(Jogador jogador){
        return jogadorRepository.save(jogador);
    }

    public void delete(String id){
        jogadorRepository.deleteById(id);
    }

    public Jogador atualizar(Jogador jogador){
        Jogador jogadorAtu = findById(jogador.getId());
        atualizarEstatisticas(jogadorAtu, jogador);
        return jogadorRepository.save(jogadorAtu);
    }

    public void atualizarEstatisticas(Jogador jogadorAtu, Jogador jogador){
        jogadorAtu.setGol(jogador.getGol());
        jogadorAtu.setAss(jogador.getAss());
    }
}
