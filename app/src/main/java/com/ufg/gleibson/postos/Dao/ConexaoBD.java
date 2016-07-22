package com.ufg.gleibson.postos.Dao;

import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ufg.gleibson.postos.Model.Combustivel;
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
    private String link = "https://inner-replica-134523.firebaseio.com/";
    private DatabaseReference dbr = FirebaseDatabase.getInstance().getReference(link);
    private String colecao = "postos";

    /**
     * Envia instancias de postos ao banco FireBase.
     * @param posto
     */
    public void guardarNovoPosto(Posto posto) {
        dbr.child(colecao).child(posto.getLatLng().toString()).setValue(posto);
    }

    /**
     * Atualiza algum atributo de um posto tal qual especificado na String atributo. Identifica
     * o posto por seu id.
     * @param identify
     * @param atributo
     */
    public void atualizarAtributoPosto(int identify, String atributo) {
        String id = Integer.toString(identify);
        dbr.child(colecao).child(id).child(atributo).setValue(atributo);
    }

    /**
     * Atualiza a lista de combustiveis de um posto. Identifica o posto por seu id.
     * @param identify
     * @param atributo
     */
    public void atualizarCombustivelPosto(int identify, List<Combustivel> atributo) {
        String id = Integer.toString(identify);
        dbr.child(colecao).child(id).child("combustiveis").setValue(atributo);
    }

    /**
     * Exclui do Firebase, o posto identificado no id.
     * @param identify
     */
    public void excluirPosto(int identify) {
        String id = Integer.toString(identify);
        dbr.child(colecao).child(id).removeValue();
    }

    /**
     * Busca instancias de postos no banco do FireBase
     * @param identify
     * @return
     */
    public Object buscarPosto(int identify) {
        String id = Integer.toString(identify);
//        return  dbr.child(colecao).child(id).equals(Posto.class);
        dbr.child(colecao).child(id).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Posto posto = dataSnapshot.getValue(Posto.class);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("getUser:onCancelled", databaseError.toException());
                    }
                }
        );
        return dbr;
    }

    public Object buscarPostoByLatLng(String latLng) {
        dbr.child(colecao).child(latLng.toString()).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Posto posto = dataSnapshot.getValue(Posto.class);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("getUser:onCancelled", databaseError.toException());
                    }
                }
        );
        return dbr;
    }
}
