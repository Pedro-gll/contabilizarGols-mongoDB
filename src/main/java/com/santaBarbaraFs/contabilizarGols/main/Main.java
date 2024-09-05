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
            System.out.println("""
                     ____       ______      __  __      ______    ______        ____       ______      ____        ____       ______      ____        ______        ____     ____      \s
                    /\\  _`\\    /\\  _  \\    /\\ \\/\\ \\    /\\__  _\\  /\\  _  \\      /\\  _`\\    /\\  _  \\    /\\  _`\\     /\\  _`\\    /\\  _  \\    /\\  _`\\     /\\  _  \\      /\\  _`\\  /\\  _`\\    \s
                    \\ \\,\\L\\_\\  \\ \\ \\L\\ \\   \\ \\ `\\\\ \\   \\/_/\\ \\/  \\ \\ \\L\\ \\     \\ \\ \\L\\ \\  \\ \\ \\L\\ \\   \\ \\ \\L\\ \\   \\ \\ \\L\\ \\  \\ \\ \\L\\ \\   \\ \\ \\L\\ \\   \\ \\ \\L\\ \\     \\ \\ \\L\\_\\\\ \\,\\L\\_\\  \s
                     \\/_\\__ \\   \\ \\  __ \\   \\ \\ , ` \\     \\ \\ \\   \\ \\  __ \\     \\ \\  _ <'  \\ \\  __ \\   \\ \\ ,  /    \\ \\  _ <'  \\ \\  __ \\   \\ \\ ,  /    \\ \\  __ \\     \\ \\  _\\/ \\/_\\__ \\  \s
                       /\\ \\L\\ \\  \\ \\ \\/\\ \\   \\ \\ \\`\\ \\     \\ \\ \\   \\ \\ \\/\\ \\     \\ \\ \\L\\ \\  \\ \\ \\/\\ \\   \\ \\ \\\\ \\    \\ \\ \\L\\ \\  \\ \\ \\/\\ \\   \\ \\ \\\\ \\    \\ \\ \\/\\ \\     \\ \\ \\/    /\\ \\L\\ \\\s
                       \\ `\\____\\  \\ \\_\\ \\_\\   \\ \\_\\ \\_\\     \\ \\_\\   \\ \\_\\ \\_\\     \\ \\____/   \\ \\_\\ \\_\\   \\ \\_\\ \\_\\   \\ \\____/   \\ \\_\\ \\_\\   \\ \\_\\ \\_\\   \\ \\_\\ \\_\\     \\ \\_\\    \\ `\\____\\
                        \\/_____/   \\/_/\\/_/    \\/_/\\/_/      \\/_/    \\/_/\\/_/      \\/___/     \\/_/\\/_/    \\/_/\\/ /    \\/___/     \\/_/\\/_/    \\/_/\\/ /    \\/_/\\/_/      \\/_/     \\/_____/ """);
            System.out.println("\n========================================================");
            System.out.println("   Bem-vindo ao Sistema de Gerenciamento de Jogadores   ");
            System.out.println("========================================================");
            System.out.println("""
                            Selecione a operação desejada:
                            1 - Adicionar jogador
                            2 - Remover jogador
                            3 - Atualizar dados de jogador
                            4 - Sair
                            """);
            System.out.println("=========================================================");
            System.out.print("Opção: ");
            op = tc.nextInt();
            tc.nextLine();
            System.out.println("=========================================================");

            switch (op) {
                case 1:
                    System.out.print("Digite o nome do jogador: ");
                    String nome = tc.nextLine();
                    Jogador obj = new Jogador(null, nome, 0, 0);
                    jogadorRepository.save(obj);
                    System.out.println("\nJogador '" + nome + "' adicionado com sucesso!");
                    break;

                case 2:
                    System.out.print("Digite o ID do jogador para deletar: ");
                    String id = tc.next();
                    Optional<Jogador> obj2 = jogadorRepository.findById(id);
                    if (obj2.isPresent()) {
                        jogadorRepository.delete(obj2.get());
                        System.out.println("\nJogador removido com sucesso!");
                    } else {
                        System.out.println("\nJogador com ID '" + id + "' não encontrado!");
                    }
                    break;

                case 3:
                    System.out.print("Digite o ID do jogador a ser atualizado: ");
                    id = tc.next();

                    Jogador jogador = jogadorRepository.findById(id).orElse(null);

                    if (jogador != null) {
                        System.out.print("Gols: ");
                        int gols = tc.nextInt();
                        System.out.print("Assistências: ");
                        int assistencias = tc.nextInt();

                        jogador.setGol(jogador.getGol() + gols);
                        jogador.setAss(jogador.getAss() + assistencias);

                        jogadorRepository.save(jogador);
                        System.out.println("\nJogador atualizado com sucesso!");
                    } else {
                        System.out.println("\nJogador com ID '" + id + "' não encontrado!");
                    }
                    break;

                case 4:
                    System.out.println("\nEncerrando atualizações... Até a próxima!");
                    break;

                default:
                    System.out.println("\nOpção inválida! Por favor, tente novamente.");
            }
            System.out.println("=========================================================");
        }
    }
}
