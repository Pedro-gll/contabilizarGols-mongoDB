package com.santaBarbaraFs.contabilizarGols.service;

import com.santaBarbaraFs.contabilizarGols.entites.Jogador;
import com.santaBarbaraFs.contabilizarGols.repository.JogadorRepository;
import com.santaBarbaraFs.contabilizarGols.service.exceptions.ObjectNotFoundException;
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
        return jogador.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public Jogador findByNome(String nome){
        List<Jogador> jogador = jogadorRepository.findByNome(nome);
        if(jogador.isEmpty()){
            throw new RuntimeException("Jogador com "+ nome+" não encontrado");
        }
        return jogador.get(0);
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
