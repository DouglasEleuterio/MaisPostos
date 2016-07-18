package com.ufg.gleibson.postos.Controler;

import android.app.SearchManager;
import android.content.Context;
import android.support.v7.internal.app.ToolbarActionBar;
import android.support.v7.internal.widget.ToolbarWidgetWrapper;
import android.support.v7.widget.Toolbar;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ufg.gleibson.postos.Model.Combustivel;
import com.ufg.gleibson.postos.Model.Posto;
import com.ufg.gleibson.postos.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InicioActivity extends FragmentActivity implements OnMapReadyCallback {

//    private ControleActivity controleActivity = new ControleActivity();

    private void initMap() {
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        initMap();
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolBarInicio);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng goiania = new LatLng(-16.6808663, -49.2532674);
        int zoomInicial = 12;
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(goiania));
        googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(goiania, zoomInicial), 1500, null);

//        for (Posto posto : controleActivity.getListaPosto()) {
//            googleMap.addMarker(new MarkerOptions().position(posto.getLatLng()).title(posto.getNome()));
//            googleMap.setOnMarkerClickListener(posto);
//        }
        LatLng goiania2 = new LatLng(-16.676539, -49.243898);
        googleMap.addMarker(new MarkerOptions().position(goiania).title("Posto1"));
        googleMap.addMarker(new MarkerOptions().position(goiania2).title("Posto2"));

    }

    public void novoPosto(View view) {
        Intent intent = new Intent(this, NovoPostoActivity.class);
        startActivity(intent);
    }

    public void login(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void posto(View view) {
        Intent intent = new Intent(this, PostoActivity.class);
        startActivity(intent);
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        return true;
    }*/
}
