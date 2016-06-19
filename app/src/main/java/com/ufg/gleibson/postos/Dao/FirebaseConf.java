package com.ufg.gleibson.postos.Dao;

import com.google.android.gms.vision.text.internal.client.SymbolBoxParcel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

/**
 * Created by gleibson on 17/06/16.
 */
public class FirebaseConf {
    /**
     * Variável para ler e escrever no Firebase
     */
    private Firebase fb = new Firebase("https://inner-replica-134523.firebaseio.com/");

    /**
     * Método para envio de objetos json ao banco do FireBase
     * @param msg
     * @param valor
     */
    public void enviar(String msg, Object valor){
        fb.child(msg).setValue(valor);
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
