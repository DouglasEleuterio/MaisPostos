package com.ufg.gleibson.postos.Controler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ufg.gleibson.postos.Dao.ConexaoBD;
import com.ufg.gleibson.postos.R;

public class LoginActivity extends AppCompatActivity {
    private ConexaoBD bd = new ConexaoBD();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
    }

    public void comecar() {

    }
}
