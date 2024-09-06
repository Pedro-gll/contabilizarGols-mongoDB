package com.santaBarbaraFs.contabilizarGols.entites;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Document(collection = "jogador")
public class Jogador implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String nome;
    private Integer gol = 0;
    private Integer ass = 0;
    private Integer gA = 0;

    public Jogador() {
    }

    public Jogador(String id, String nome, Integer gol, Integer ass) {
        this.id = id;
        this.nome = nome;
        this.gol = gol;
        this.ass = ass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getGol() {
        return gol;
    }

    public void setGol(Integer gol) {
        this.gol = gol;
        atualizarGa();
    }

    public Integer getAss() {
        return ass;
    }


    public void setAss(Integer ass) {
        this.ass = ass;
        atualizarGa();
    }

    public Integer getGa() {
        return gA;
    }

    private void atualizarGa() {
        this.gA = this.gol + this.ass;
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

    public void criarExcel(List<Jogador> jogadores) {
        String path = "C:\\Users\\ADM\\Desktop\\input2.csv";
        //jogadores = jogadorRepository.findAll();

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jogador jogador = (Jogador) o;
        return id == jogador.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
        public String toString () {
            DecimalFormat df = new DecimalFormat("00");
            return nome.toUpperCase() + " | Gols: " + df.format(gol) + " | Ass: " + df.format(ass) + " | G/A: " + df.format(gA);

        }
    }

