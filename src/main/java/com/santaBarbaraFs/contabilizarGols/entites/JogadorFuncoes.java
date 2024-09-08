package com.santaBarbaraFs.contabilizarGols.entites;

import com.santaBarbaraFs.contabilizarGols.repository.JogadorRepository;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class JogadorFuncoes extends Jogador{
    Scanner tc = new Scanner(System.in);
    JogadorRepository jogadorRepository;
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

    public void criarExcel(List<Jogador> jogadores) {
        String path = "C:\\Users\\ADM\\Desktop\\input2.csv";
        jogadores = jogadorRepository.findAll();

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
}
