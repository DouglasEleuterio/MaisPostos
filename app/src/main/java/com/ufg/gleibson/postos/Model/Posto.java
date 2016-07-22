package com.ufg.gleibson.postos.Model;

import com.google.android.gms.maps.model.LatLng;

import java.util.*;

/**
 * Created by gleibson on 19/06/16.
 */
public class Posto {
    private String nome;
    private String bandeira;
    private int nota;
    private LatLng latLng;
    private float gasolina;
    private float alcool;
    private float diesel;

    public Posto(String nome, String bandeira, int nota, LatLng latLng, float gasolina,
                 float alcool, float diesel) {
        this.nome = nome;
        this.bandeira = bandeira;
        this.nota = nota;
        this.latLng = latLng;
        this.gasolina = gasolina;
        this.alcool = alcool;
        this.diesel = diesel;
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

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public float getGasolina() {
        return gasolina;
    }

    public void setGasolina(float gasolina) {
        this.gasolina = gasolina;
    }

    public float getAlcool() {
        return alcool;
    }

    public void setAlcool(float alcool) {
        this.alcool = alcool;
    }

    public float getDiesel() {
        return diesel;
    }

    public void setDiesel(float diesel) {
        this.diesel = diesel;
    }
}
