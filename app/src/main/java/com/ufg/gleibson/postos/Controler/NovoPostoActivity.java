package com.ufg.gleibson.postos.Controler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;
import com.ufg.gleibson.postos.Dao.Controle;
import com.ufg.gleibson.postos.Model.Combustivel;
import com.ufg.gleibson.postos.Model.Posto;
import com.ufg.gleibson.postos.R;

import java.util.ArrayList;
import java.util.List;

public class NovoPostoActivity extends AppCompatActivity {

    private Controle controle = new Controle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String coordenadas = intent.getStringExtra("coordenadas");
        if (!(coordenadas.equals("")) && (coordenadas!=null)) {
            carregaPostoCadastrado(coordenadas);
        }
        setContentView(R.layout.activity_novo_posto);
        initToolbar();

        EditText nome = (EditText) findViewById(R.id.edit_text_nome);
        EditText bandeira = (EditText) findViewById(R.id.edit_text_bandeira);
        EditText gas = (EditText) findViewById(R.id.edit_text_gasolina);
        EditText gasAdit = (EditText) findViewById(R.id.edit_text_gasolina_aditivada);
        EditText alc = (EditText) findViewById(R.id.edit_text_alcool);
        EditText alcAdit = (EditText) findViewById(R.id.edit_text_alcool_aditivado);
        EditText die = (EditText) findViewById(R.id.edit_text_diesel);
        EditText dieS10 = (EditText) findViewById(R.id.edit_text_diesel_s10);

        List<Combustivel> listaComb = new ArrayList<>();

    }

    private void carregaPostoCadastrado(String coordenadas) {
        Posto posto = controle.getPostoByLatLng(coordenadas);

        EditText nome = (EditText) findViewById(R.id.edit_text_nome);
        EditText bandeira = (EditText) findViewById(R.id.edit_text_bandeira);
        EditText gas = (EditText) findViewById(R.id.edit_text_gasolina);
        EditText gasAdit = (EditText) findViewById(R.id.edit_text_gasolina_aditivada);
        EditText alc = (EditText) findViewById(R.id.edit_text_alcool);
        EditText alcAdit = (EditText) findViewById(R.id.edit_text_alcool_aditivado);
        EditText die = (EditText) findViewById(R.id.edit_text_diesel);
        EditText dieS10 = (EditText) findViewById(R.id.edit_text_diesel_s10);

        nome.setText(posto.getNome());
        bandeira.setText(posto.getBandeira());

        /*
        * Gleibson, ficou faltando aqui, passar os bomcustíveis aqui.
        * */
    }

    private String viewToStr(EditText view){
        return view.getText().toString();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBarNovoPosto);
        toolbar.setTitle("Novo Posto");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    public void salvarPosto(View view, Posto posto){
        controle.incluirNovoPosto(posto);
    }
}
