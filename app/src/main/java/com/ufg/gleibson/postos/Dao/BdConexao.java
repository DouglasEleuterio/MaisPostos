package com.ufg.gleibson.postos.Dao;

import com.google.gson.Gson;
import com.ufg.gleibson.postos.Model.Combustivel;
import com.ufg.gleibson.postos.Model.Posto;

/**
 * Created by gleibson on 17/06/16.
 */
public class BdConexao {
    private FirebaseConf banco = new FirebaseConf();

    /**
     * Método para enviar ao banco, um novo posto(Objeto) cadastrado na aplicação
     * @param posto
     */
    public void enviarNovoPosto(Posto posto){
        Gson gson = new Gson();
        String novoPostoJSON = gson.toJson(posto);
        banco.enviar(novoPostoJSON);
    }

    /**
     * Método para buscar no banco um posto cadastrado
     * @param nome
     * @return
     */
    public Object buscarPosto(String nome) {
        Gson gson = new Gson();
        Posto posto = gson.fromJson(banco.buscar(nome), Posto.class);
        return posto;
    }

    //       FileWriter writer = new FileWriter("C:\\file.json");
    //       writer.write(json);
    //       writer.close();
}
