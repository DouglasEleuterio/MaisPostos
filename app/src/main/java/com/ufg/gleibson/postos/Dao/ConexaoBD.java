package com.ufg.gleibson.postos.Dao;

import com.firebase.client.Firebase;
import com.ufg.gleibson.postos.Model.Posto;

/**
 * Created by gleibson on 17/06/16.
 */
public class ConexaoBD {
    /**
     * Variáveis para ler e escrever no Firebase
     */
    private String link = null;
    private Firebase firebase = null;
    private String colecaoPosto = "postos";

    /**
     * Cria uma conexão única com o banco de dados.
     */
    public ConexaoBD() {
        this.link = "https://maispostos.firebaseio.com";
        if (firebase == null) {
            this.firebase = new Firebase(link);
        }
    }

    /**
     * Envia instancias de postos ao banco FireBase.
     * @param posto
     */
    public void guardarNovoPosto(Posto posto) {
        String id = removeCaracters(posto.getLatLng().toString());
        Firebase postoDataBase = firebase.child(colecaoPosto).child(id);
        postoDataBase.setValue(posto);
    }

    /**
     * Atualiza algum atributo de um posto tal qual especificado na String atributo. Identifica
     * o posto por seu id.
     * @param identify
     * @param atributo
     */
    public void atualizarAtributoPosto(int identify, String atributo, String atualizar) {
        firebase = firebase.child(colecaoPosto);
        firebase.setValue(atualizar);
    }

    /**
     * Exclui do Firebase, o posto identificado no id.
     * @param identify
     */
    public void excluirPosto(int identify) {
        String id = Integer.toString(identify);
        firebase = firebase.child(colecaoPosto).child(id);
        firebase.removeValue();
    }

    /**
     * Busca instancias de postos no banco do FireBase
     * @param identify
     * @return
     */
    public Object buscarPosto(int identify) {
        return null;
    }

    public Object buscarPostoByLatLng(String latLng) {
        return null;
    }

    private String removeCaracters(String position) {
        String string =  position.replace("lat/lng: (","");
        string = string.replace(")","");
        string = string.replace(".","");
        string = string.replace("-","");
        return string;
    }
}
