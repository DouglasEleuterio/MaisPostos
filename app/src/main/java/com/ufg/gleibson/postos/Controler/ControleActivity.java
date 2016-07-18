package com.ufg.gleibson.postos.Controler;

import com.ufg.gleibson.postos.Dao.ConexaoBD;
import com.ufg.gleibson.postos.Model.Posto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gleibson on 15/07/16.
 */
public class ControleActivity {

    private ConexaoBD banco = new ConexaoBD();

    public List<Posto> getListaPosto() {
        List<Posto> lista = new ArrayList<Posto>();
        boolean tem = true;
        while (tem) {
            lista.add((Posto) banco.buscarPosto(1));
            tem = false;
        }
        return lista;
    }

}
