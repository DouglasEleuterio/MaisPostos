package com.ufg.gleibson.postos.Model;

import java.util.*;

/**
 * Created by gleibson on 19/06/16.
 */
public class Posto {
    private String nome;
    private String bandeira;
    private List<Combustivel> combustiveis;
    private int nota;

    public Posto(String nome, String bandeira) {
        this.nome = nome;
        this.bandeira = bandeira;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public List<Combustivel> getCombustivel() {
        return combustiveis;
    }

    public void setCombustiveis(List<Combustivel> combustiveis) {
        this.combustiveis = combustiveis;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
}
