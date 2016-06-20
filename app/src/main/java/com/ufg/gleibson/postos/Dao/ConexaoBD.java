package com.ufg.gleibson.postos.Dao;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ufg.gleibson.postos.Model.Posto;

import java.util.Map;

/**
 * Created by gleibson on 17/06/16.
 */
public class ConexaoBD {
    /**
     * Variável para ler e escrever no Firebase
     */
    private Firebase fb = new Firebase("https://inner-replica-134523.firebaseio.com/");
    private DatabaseReference dbr = FirebaseDatabase.getInstance().getReference();

    /**
     * Método para envio de objetos postos ao banco do FireBase (transformados em JSON)
     * @param id
     * @param posto
     */
    public void escreverNovoPosto(String id, Posto posto) {
        dbr.child("postos").child(id).setValue(posto);
    }

    /**
     * Método para buscar objetos json do banco do FireBase
     * @param msg
     * @return
     */
    public String buscar(String msg){
        fb.child(msg).addValueEventListener(new ValueEventListener(){
            @Override
           public void onDataChange(DataSnapshot snapshot){
               String valor = snapshot.getValue().toString();
           }
            @Override
            public void onCancelled(FirebaseError error){ }
        });
        return valor;
    }

    /**
     * Método para criar um usuário com email e senha
     * @param email
     * @param senha
     */
    public void criarUsuario(String email, String senha){
        fb.createUserWithEmailAndPassword();
        fb.createUser(email, senha, new Firebase.ValueResultHandler<Map<String, Object>>(){
           @Override
            public void onSuccess(Map<String, Object> result) {
                System.out.println(result.get("uid")+" criado com sucesso!");
            }
            @Override public void onError(FirebaseError firebaseError){ }
        });
    }

    /**
     * Método para confirmar o login
     * @param email
     * @param senha
     */
    public void logarUsuario(String email, String senha){
        fb.authwithPassword(email, senha, new Firebase.AuthResultHandler(){
            @Override
            public void onAuthenticated(AuthData authData){
                System.out.println("ID: "+authData.getUid()+", Senha: "+authData.getProvider());
            }
            public void onAuthentictionError(FirebaseError firebaseError){ }
        });
    }
}
