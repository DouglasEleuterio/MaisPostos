package com.ufg.gleibson.postos.Controler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;
import com.ufg.gleibson.postos.Dao.Controle;
import com.ufg.gleibson.postos.Model.Posto;
import com.ufg.gleibson.postos.R;


public class NovoPostoActivity extends AppCompatActivity {

    private Controle controle = new Controle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_posto);
        initToolbar();
        LatLng latLng;
        Intent i = getIntent();
        String localizacao = i.getStringExtra("latlng");
        if ((localizacao.equals("")) || (localizacao==null)) {
            finish();
        }
        latLng = stringToLatLng(localizacao);

        Posto posto = getDadosPosto(latLng);
        salvarPosto(posto);
    }

    private Posto getDadosPosto(LatLng latLng){
        EditText n = (EditText) findViewById(R.id.edit_text_nome);
        EditText bd = (EditText) findViewById(R.id.edit_text_bandeira);
        EditText gas = (EditText) findViewById(R.id.edit_text_gasolina);
        EditText alc = (EditText) findViewById(R.id.edit_text_alcool);
        EditText die = (EditText) findViewById(R.id.edit_text_diesel);
        EditText nt = (EditText) findViewById(R.id.edit_text_atendimento);

        String nome = n.getText().toString();
        String bandeira = bd.getText().toString();
        float gasolina = Float.parseFloat(gas.getText().toString());
        float alcool = Float.parseFloat(alc.getText().toString());
        float diesel = Float.parseFloat(die.getText().toString());
        int nota = Integer.parseInt(nt.getText().toString());

        Posto posto = new Posto(nome, bandeira, nota, latLng, gasolina, alcool, diesel);
        return posto;
    }

    private void carregaPostoCadastrado(LatLng latLng) {
        Posto posto = controle.getPostoByLatLng(latLng);

        /*Não sei se esse método consegue colocar nos EditTexts os valores do posto
        ele tem q receber um LatLng como o de cima ai e buscar no banco por este atributo
        temos que ver como fazer essa atualização, acho que teremos que fazer uma nova activity
        pq não da pra setar valores nos EditTexts */

        //nome.setText(posto.getNome());
        //bandeira.setText(posto.getBandeira());
    }

    public void salvarPosto(Posto posto){
        controle.incluirNovoPosto(posto);
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBarNovoPosto);
        toolbar.setTitle("Novo Posto");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private LatLng stringToLatLng(String position) {
        String[] ll = position.split(",");
        double latitude = Double.parseDouble(ll[0]);
        double longitude = Double.parseDouble(ll[1]);
        return new LatLng(latitude, longitude);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

}
