package com.ufg.gleibson.postos.Controler;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.firebase.client.Firebase;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ufg.gleibson.postos.Dao.Controle;
import com.ufg.gleibson.postos.Model.Posto;
import com.ufg.gleibson.postos.R;

public class InicioActivity extends AppCompatActivity implements OnMapReadyCallback {

  //  private Controle controle = new Controle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_inicio);
        initMap();
        initToolbar();
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        LatLng inicial = new LatLng(-16.6808663, -49.2532674);
        int zoomInicial = 15;
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(inicial));
        googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(inicial, zoomInicial), 1500, null);
        googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                novoPosto(latLng);
            }
        });
        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                    posto(marker.getPosition());
            }
        });
        googleMap.getUiSettings().setMapToolbarEnabled(false);

 /*       for (Posto posto : controle.getListaPosto()) {
            googleMap.addMarker(new MarkerOptions().position(posto.getLatLng())
                    .title(posto.getNome()).snippet("Nota: "+ String.valueOf(posto.getNota()))
                    .icon(BitmapDescriptorFactory
                            .fromBitmap(initMarker(R.drawable.ic_local_gas_24dp))));
        }*/

        LatLng posto2 = new LatLng(-16.676539, -49.243898);
        googleMap.addMarker(new MarkerOptions().position(inicial).title("Posto1").snippet("Nota: 8")
                .icon(BitmapDescriptorFactory.fromBitmap(initMarker(R.drawable.ic_local_gas_24dp))));
        googleMap.addMarker(new MarkerOptions().position(posto2).title("Posto2").snippet("Nota: 6")
                .icon(BitmapDescriptorFactory.fromBitmap(initMarker(R.drawable.ic_local_gas_24dp))));

    }

    private Bitmap initMarker(int i) {
        Drawable d = getResources().getDrawable(i);
        Bitmap bitmap = Bitmap.createBitmap(d.getIntrinsicWidth(), d.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas ca = new Canvas();
        ca.setBitmap(bitmap);
        d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
        d.draw(ca);
        return bitmap;
    }

    private void initMap() {
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBarInicio);
        toolbar.setTitle("+Postos");
        setSupportActionBar(toolbar);
    }

    private void novoPosto(LatLng latLng) {
        Intent intent = new Intent(this, NovoPostoActivity.class);
        intent.putExtra("latlng", latLng.toString());
        startActivity(intent);
    }

    private void posto(LatLng latLng) {
        Intent intent = new Intent(this, PostoActivity.class);
        intent.putExtra("latlng", latLng.toString());
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