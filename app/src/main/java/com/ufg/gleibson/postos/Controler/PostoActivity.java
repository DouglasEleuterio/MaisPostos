package com.ufg.gleibson.postos.Controler;

import android.content.Intent;
import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.model.LatLng;
import com.ufg.gleibson.postos.R;

public class PostoActivity extends AppCompatActivity {

    private String coordenadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        coordenadas = intent.getStringExtra("coordenadas");
        setContentView(R.layout.activity_posto);
        initToolbar();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBarPosto);
        toolbar.setTitle("+Postos");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    public void editarPosto(View view){
        Intent intent = new Intent(this, NovoPostoActivity.class);
        intent.putExtra("coordenadas",coordenadas);
        startActivity(intent);
        finish();
    }
}
