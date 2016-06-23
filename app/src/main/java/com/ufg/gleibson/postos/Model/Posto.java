package com.ufg.gleibson.postos.Model;

import java.util.*;

/**
 * Created by gleibson on 19/06/16.
 */
public class Posto {
    private int id;
    private String nome;
    private String bandeira;
    private List<Combustivel> combustiveis;
    private int nota;
    private double lat;
    private double lon;

    public Posto(int id, String nome, String bandeira, double lat, double lon) {
        this.id = id;
        this.nome = nome;
        this.bandeira = bandeira;
        this.lat = lat;
        this.lon = lon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Combustivel> getCombustiveis() {
        return combustiveis;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
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
