package com.santaBarbaraFs.contabilizarGols.main;

import com.santaBarbaraFs.contabilizarGols.entites.Jogador;
import com.santaBarbaraFs.contabilizarGols.entites.JogadorFuncoes;
import com.santaBarbaraFs.contabilizarGols.repository.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Main implements CommandLineRunner {
    Scanner tc = new Scanner(System.in);

    @Autowired
    private JogadorRepository jogadorRepository;
    @Autowired
    private JogadorFuncoes fun;

    @Override
    public void run(String... args) throws Exception {
        String path = "C:\\Users\\ADM\\Desktop\\jogadores.csv";
        String pathLista ="C:\\Users\\ADM\\Desktop\\testeAtua.txt";
        String id;

        System.out.println("""
                     ____       ______      __  __      ______    ______        ____       ______      ____        ____       ______      ____        ______        ____     ____      \s
                    /\\  _`\\    /\\  _  \\    /\\ \\/\\ \\    /\\__  _\\  /\\  _  \\      /\\  _`\\    /\\  _  \\    /\\  _`\\     /\\  _`\\    /\\  _  \\    /\\  _`\\     /\\  _  \\      /\\  _`\\  /\\  _`\\    \s
                    \\ \\,\\L\\_\\  \\ \\ \\L\\ \\   \\ \\ `\\\\ \\   \\/_/\\ \\/  \\ \\ \\L\\ \\     \\ \\ \\L\\ \\  \\ \\ \\L\\ \\   \\ \\ \\L\\ \\   \\ \\ \\L\\ \\  \\ \\ \\L\\ \\   \\ \\ \\L\\ \\   \\ \\ \\L\\ \\     \\ \\ \\L\\_\\\\ \\,\\L\\_\\  \s
                     \\/_\\__ \\   \\ \\  __ \\   \\ \\ , ` \\     \\ \\ \\   \\ \\  __ \\     \\ \\  _ <'  \\ \\  __ \\   \\ \\ ,  /    \\ \\  _ <'  \\ \\  __ \\   \\ \\ ,  /    \\ \\  __ \\     \\ \\  _\\/ \\/_\\__ \\  \s
                       /\\ \\L\\ \\  \\ \\ \\/\\ \\   \\ \\ \\`\\ \\     \\ \\ \\   \\ \\ \\/\\ \\     \\ \\ \\L\\ \\  \\ \\ \\/\\ \\   \\ \\ \\\\ \\    \\ \\ \\L\\ \\  \\ \\ \\/\\ \\   \\ \\ \\\\ \\    \\ \\ \\/\\ \\     \\ \\ \\/    /\\ \\L\\ \\\s
                       \\ `\\____\\  \\ \\_\\ \\_\\   \\ \\_\\ \\_\\     \\ \\_\\   \\ \\_\\ \\_\\     \\ \\____/   \\ \\_\\ \\_\\   \\ \\_\\ \\_\\   \\ \\____/   \\ \\_\\ \\_\\   \\ \\_\\ \\_\\   \\ \\_\\ \\_\\     \\ \\_\\    \\ `\\____\\
                        \\/_____/   \\/_/\\/_/    \\/_/\\/_/      \\/_/    \\/_/\\/_/      \\/___/     \\/_/\\/_/    \\/_/\\/ /    \\/___/     \\/_/\\/_/    \\/_/\\/ /    \\/_/\\/_/      \\/_/     \\/_____/ """);

        int op = 0;
        while (op != 6) {
            System.out.println("\n========================================================");
            System.out.println("   Bem-vindo ao Sistema de Gerenciamento de Jogadores   ");
            System.out.println("========================================================");
            System.out.println("""
                            Selecione a operação desejada:
                            1 - Adicionar jogador
                            2 - Remover jogador
                            3 - Atualizar dados de jogador
                            4 - Mostrar artilharia
                            5 - Criar tabela Excel
                            6 - Sair
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
                    fun.adicionar(nome);
                    break;

                case 2:
                    System.out.print("Digite o ID do jogador para deletar: ");
                    id = tc.next();
                    fun.deletar(id);
                    break;
                case 3:
                    System.out.println("Dejesa atualizar manualmente ou enviar uma lista? " +
                            "[1] - Manualmente || [2] - Lista");

                    int n = tc.nextInt();

                    if (n == 1) {
                       fun.atualizarManualmente();

                    }else{
                       fun.atualizarPorLista(pathLista);
                    }

                    break;

                case 4:

                    System.out.println("""
                            Dejesa ordenar a lista por:
                            1 - GOl
                            2 - ASSISTENCIA
                            3 - G/A
                            """);
                    System.out.println("=========================================================");
                    System.out.print("Opção: ");
                    op = tc.nextInt();

                    List<Jogador> lista_jogadores = jogadorRepository.findAll();
                    fun.ordenar(lista_jogadores, op);
                    break;

                case 5:
                    List<Jogador> lista_jogadores1 = jogadorRepository.findAll();
                    fun.criarExcel(lista_jogadores1,path);
                    break;

                case 6:
                    System.out.println("\nEncerrando atualizações... Até a próxima!");
                    break;

                default:
                    System.out.println("\nOpção inválida! Por favor, tente novamente.");
            }
            System.out.println("=========================================================");
        }
    }
}

