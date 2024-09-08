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

