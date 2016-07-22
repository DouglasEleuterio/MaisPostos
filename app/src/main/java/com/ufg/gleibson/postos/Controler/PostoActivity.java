package com.ufg.gleibson.postos.Controler;

import android.content.Intent;
import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.model.LatLng;
import com.ufg.gleibson.postos.Dao.Controle;
import com.ufg.gleibson.postos.Model.Posto;
import com.ufg.gleibson.postos.R;

public class PostoActivity extends AppCompatActivity {

    private Controle controle = new Controle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posto);
        Intent i = getIntent();
        String localizacao = i.getStringExtra("latlng");
        if ((localizacao.equals("")) || (localizacao == null)) {
            finish();
        }
        LatLng latLng = stringToLatLng(localizacao);

        Posto posto = buscarPosto(latLng);
        //Implementar aqui como será setado os valores deste posto nos textViews da tela "Posto"

        initToolbar(posto);
    }

    private Posto buscarPosto(LatLng latLng){
        return controle.getPostoByLatLng(latLng);
    }

    public void editarPosto(View view){
        Intent intent = new Intent(this, NovoPostoActivity.class);
        //intent.putExtra("coordenadas",coordenadas); este método precisa enviar um LatLng para o outro
        startActivity(intent);
        finish();
    }

    private void initToolbar(Posto posto) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBarPosto);
        toolbar.setTitle(posto.getNome());
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
