package com.ufg.gleibson.postos.Dao;

import com.google.android.gms.maps.model.LatLng;
import com.ufg.gleibson.postos.Dao.ConexaoBD;
import com.ufg.gleibson.postos.Model.Posto;

import java.lang.annotation.Retention;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by gleibson on 15/07/16.
 */
public class Controle {

    private ConexaoBD banco = null;

    public Controle() {
        this.banco = new ConexaoBD();
    }

    public List<Posto> getListaPosto() {
        List<Posto> lista = new ArrayList<Posto>();
        boolean tem = true;
        while (tem) {
            lista.add((Posto) banco.buscarPosto(1));
            tem = false;
        }
        return lista;
    }

    public Posto getPostoByLatLng(String latLng) {
        return (Posto) banco.buscarPostoByLatLng(latLng);
    }

    public void incluirNovoPosto(Posto posto){
        banco.guardarNovoPosto(posto);
    }

}
