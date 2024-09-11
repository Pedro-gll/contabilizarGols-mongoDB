package com.santaBarbaraFs.contabilizarGols.resource;

import com.santaBarbaraFs.contabilizarGols.entites.Jogador;
import com.santaBarbaraFs.contabilizarGols.service.JogadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value="/jogadores")
public class JogadorResource {

    @Autowired
    private JogadorService jogadorService;

   @GetMapping
    public ResponseEntity<List<Jogador>> findAll() {
        List<Jogador> jogadores = jogadorService.findAll();
        return ResponseEntity.ok().body(jogadores);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Jogador> findById(@PathVariable String id) {
       Jogador jogador = jogadorService.findById(id);
       return ResponseEntity.ok().body(jogador);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Jogador> findByNome(@PathVariable String nome) {
        try {
            Jogador jogador = jogadorService.findByNome(nome);
            return ResponseEntity.ok().body(jogador);
        }catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Jogador> save(@RequestBody Jogador jogador) {
       Jogador obj = jogadorService.inserir(jogador);
       return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
       jogadorService.delete(id);
       return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Jogador> update(@PathVariable String id, @RequestBody Jogador jogador) {
       Jogador obj = jogadorService.atualizar(jogador);
       return ResponseEntity.ok().body(obj);
    }

}
