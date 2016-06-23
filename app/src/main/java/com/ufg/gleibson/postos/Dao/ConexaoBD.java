package com.ufg.gleibson.postos.Dao;

import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.util.Log;

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
import com.ufg.gleibson.postos.Model.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by gleibson on 17/06/16.
 */
public class ConexaoBD {
    /**
     * Variáveis para ler e escrever no Firebase
     */
    private String link = "https://inner-replica-134523.firebaseio.com/";
//    private Firebase = new Firebase(link);
    private DatabaseReference dbr = FirebaseDatabase.getInstance().getReference();
    private FirebaseUser usuario = FirebaseAuth.getInstance().getCurrentUser();
    private FirebaseAuth fa = FirebaseAuth.getInstance();
    private String colecao = "postos";

    /**
     * Envia instancias de postos ao banco FireBase.
     * @param posto
     */
    public void guardarNovoPosto(Posto posto) {
        String id = Integer.toString(posto.getId());
        dbr.child(colecao).child(id).setValue(posto);
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

    /**
     * Busca um usuário com o login atual no sistema
     */
    public void autenticarUsuario() {
        new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d("Ok", "onAuthStateChanged:signed-in:" + user.getUid());
                } else {
                    Log.d("Erro", "onAuthStateChanged:signed_out");
                }
            }
        };
    }

    /**
     * Pega o usuário no Firebase e retorna uma lista com os dados do mesmo
     * @return
     */
    public List getUsuario() {
        List<String> lista = new ArrayList<String>();
        if (usuario != null) {
            String nome = usuario.getDisplayName();
            lista.add(nome);
            String email = usuario.getEmail();
            lista.add(email);
            String uid = usuario.getUid();
            lista.add(uid);
        }
        return lista;
    }

    /**
     * Atualiza o email de um usuario.
     * @param email
     */
    public void atualizarEmail(String email) {
        usuario.updateEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d("Ok", "Email atualizado!");
                }
            }
        });
    }

    /**
     * Atualiza a senha de um usuario.
     * @param senha
     */
    public void atualizarSenha(String senha) {
        usuario.updatePassword(senha).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d("Ok", "Senha atualizada!");
                }
            }
        });
    }

    /**
     * Deleta um usuário do banco.
     */
    public void excluirUsuario() {
        usuario.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d("Ok", "Usuario excluido!");
                }
            }
        });
    }

    /**
     * Criar um usuário com email e senha
     * @param email
     * @param senha
     */
    public boolean criarUsuario(String email, String senha){
        fa.createUserWithEmailAndPassword(email, senha);
        return true;
    }

    /**
     * Logar no sistema com email e senha.
     * @param email
     * @param senha
     */
    public boolean logarUsuario(String email, String senha){
        fa.signInWithEmailAndPassword(email, senha);
        return true;
    }

    /**
     * Fazer logof em um usuário
     */
    public boolean logofUsuario() {
        fa.signOut();
        return true;
    }
}
