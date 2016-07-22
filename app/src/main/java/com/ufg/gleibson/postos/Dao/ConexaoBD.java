package com.ufg.gleibson.postos.Dao;

import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.util.Log;

import com.firebase.client.Firebase;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ufg.gleibson.postos.Model.Posto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by gleibson on 17/06/16.
 */
public class ConexaoBD {
    /**
     * Variáveis para ler e escrever no Firebase
     */
    private String link = null;
    private Firebase firebase = null;
    private String colecao = "postos";


    /* Gleibson
    public ConexaoBD() {
        this.link = "https://inner-replica-134523.firebaseio.com/";
        this.dbr = FirebaseDatabase.getInstance().getReference(link);
    }  */

    /* Marcos */
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
        firebase.child(colecao).child(posto.getLatLng().toString()).setValue(posto);
    }

    /**
     * Atualiza algum atributo de um posto tal qual especificado na String atributo. Identifica
     * o posto por seu id.
     * @param identify
     * @param atributo
     */
    public void atualizarAtributoPosto(int identify, String atributo, String atualizar) {
        String id = Integer.toString(identify);
        firebase = firebase.child(colecao).child(id).child(atributo);
        firebase.setValue(atualizar);
    }

    /**
     * Exclui do Firebase, o posto identificado no id.
     * @param identify
     */
    public void excluirPosto(int identify) {
        String id = Integer.toString(identify);
        firebase = firebase.child(colecao).child(id);
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
}
