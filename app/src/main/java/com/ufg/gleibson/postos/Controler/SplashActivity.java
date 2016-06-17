package com.ufg.gleibson.postos.Controler;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ufg.gleibson.postos.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            Intent i = new Intent(this, InicioActivity.class);
            startActivity(i);
            finish();
    }
}