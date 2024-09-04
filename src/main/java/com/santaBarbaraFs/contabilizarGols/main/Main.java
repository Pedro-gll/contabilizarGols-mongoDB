package com.santaBarbaraFs.contabilizarGols.main;

import com.santaBarbaraFs.contabilizarGols.entites.Jogador;
import com.santaBarbaraFs.contabilizarGols.repository.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

@Component
public class Main implements CommandLineRunner {
    Scanner tc = new Scanner(System.in);

    @Autowired
    private JogadorRepository jogadorRepository;

    @Override
    public void run(String... args) throws Exception {

    int op = 0;
        while (op != 4) {
            System.out.println
                    ("""
                            -------Digite a operação desejada-------
                            1 - Adicionar jogador
                            2 - Remover jogador
                            3 - Atualizar dados de jogador
                            4 - Sair
                            """);
            op = tc.nextInt();
            switch (op) {
                case 1:
                    System.out.println("Digite o nome do jogador: ");
                    String nome = tc.next();
                    Jogador obj = new Jogador(null, nome, 0, 0);
                    jogadorRepository.save(obj);
                    break;

                case 2:
                    System.out.println("Digite o id do jogador para deletar: ");
                    String id = tc.next();
                    Jogador obj2 = jogadorRepository.findById(id).get();
                    jogadorRepository.delete(obj2);
                    break;

                case 3:
                    System.out.println("Digite o id do jogador a ser atualizado: ");
                    id = tc.next();

                    Jogador jogador = jogadorRepository.findById(id).orElse(null);

                    if(jogador != null) {
                        System.out.println("Gols: ");
                        int gols = tc.nextInt();
                        System.out.println("Assistencias: ");
                        int assistencias = tc.nextInt();

                        jogador.setGol(jogador.getGol()+gols);
                        jogador.setAss(jogador.getAss()+assistencias);

                        jogadorRepository.save(jogador);
                        System.out.println("Jogador atualizado com sucesso!");
                    }else {
                        System.out.println("Jogador não encontrado!");
                    }

                    break;

                case 4:
                    System.out.println("Encerrando atualizações!! ");
                    break;
            }
        }
    }
}
