package com.santaBarbaraFs.contabilizarGols.entites;

import com.santaBarbaraFs.contabilizarGols.repository.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class JogadorFuncoes {
    Scanner tc = new Scanner(System.in);
    @Autowired
    JogadorRepository jogadorRepository;

    public void atualizarManualmente() {
        System.out.print("Digite o ID do jogador a ser atualizado: ");
        String id = tc.next();

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
    }

    public void atualizarPorLista(String path){
        try (BufferedReader bf = new BufferedReader(new FileReader(path))) {
            String linha;

            while ((linha = bf.readLine()) != null) {
                String[] split = linha.split("-");
                String gol = split[0].trim();
                //String ass = split[1].trim();

                List<Jogador> lista_jogadores_gol = jogadorRepository.findByNome(gol);

                if (!lista_jogadores_gol.isEmpty()){
                    Jogador j = lista_jogadores_gol.get(0);

                    j.setGol(j.getGol()+1);


                    jogadorRepository.save(j);
                }

                //List<Jogador> lista_jogadores_ass = jogadorRepository.findByNome(ass);

                if(split.length > 1){
                    String ass = split[1].trim();
                    if(!ass.isEmpty()){
                        List<Jogador> lista_jogadores_ass = jogadorRepository.findByNome(ass);
                        if (!lista_jogadores_ass.isEmpty()){
                            Jogador j = lista_jogadores_ass.get(0);
                            j.setAss(j.getAss()+1);
                            jogadorRepository.save(j);
                        }else {
                            System.out.println("Jogador nao encontrado");
                        }
                    }
                }
            }
            try (PrintWriter writer = new PrintWriter(path)) {
                writer.print("");
            }
        }
        catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    public void ordenar(List<Jogador> jogadores, int valor) {
        switch (valor) {
            case 1:
                jogadores.sort(Comparator.comparingInt(Jogador::getGol).reversed());
                break;
            case 2:
                jogadores.sort(Comparator.comparingInt(Jogador::getAss).reversed());
                break;
            case 3:
                jogadores.sort(Comparator.comparingInt(Jogador::getGa).reversed());
                break;
            default:
                throw new IllegalArgumentException("Valor de ordenação inválido: " + valor);
        }

        for (Jogador j : jogadores) {
            while (j.getNome().length() < 8) {
                j.setNome(j.getNome() + " ");
            }
            System.out.println(j);
        }
    }

    public void criarExcel(List<Jogador> jogadores, String path) {

        try(BufferedWriter bf = new BufferedWriter(new FileWriter(path, StandardCharsets.UTF_8))) {
            bf.write("Id; Nome; Gol; Ass; G/A");
            bf.newLine();

            for (int i = 0; i < jogadores.size(); i++) {
                Jogador j = jogadores.get(i);
                bf.write(j.getId() + ";" + j.getNome() + ";" + j.getGol() + ";" + j.getAss() + ";" + j.getGa());
                bf.newLine(); // Adiciona uma nova linha para o próximo produto
            }

            System.out.println("Arquivo criado com sucesso!");
        }catch(IOException e){
            System.out.println("Erro ao escrever o arquivo: "+e.getMessage());
        }
    }

    public void deletar(String id){
        Optional<Jogador> obj2 = jogadorRepository.findById(id);
        if (obj2.isPresent()) {
            jogadorRepository.delete(obj2.get());
            System.out.println("\nJogador removido com sucesso!");
        } else {
            System.out.println("\nJogador com ID '" + id + "' não encontrado!");
        }
    }

    public void adicionar(String nome){
        Jogador obj = new Jogador(null, nome, 0, 0);
        jogadorRepository.save(obj);
        System.out.println("\nJogador '" + nome + "' adicionado com sucesso!");
    }
}
