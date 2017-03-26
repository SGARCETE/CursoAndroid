package com.example.sgarcete.mapsapp;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    @Bind(R.id.bMapa)
    Button bMapa;

    @Bind(R.id.bhibrido)
    Button bHibrido;

    @Bind(R.id.bterreno)
    Button bTerreno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        bMapa.setOnClickListener(this);
        bTerreno.setOnClickListener(this);
        bHibrido.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.bMapa:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;

            case R.id.bhibrido:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;

            case R.id.bterreno:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            break;

            default:
                break;


        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Los mapas muestran las utilidades de google maps deshabilitadas, podemos habilitarlas con este código:

        UiSettings uiSettings = mMap .getUiSettings();
        uiSettings.setZoomControlsEnabled(true); //Habilitamos el zoom

        //Acá modificamos las coordenadas, puse el de la ungs :p
        // Add a marker in UNGS and move the camera
        LatLng ungs = new LatLng(-34.521722, -58.700798);
        mMap.addMarker(new MarkerOptions().position(ungs).title("UNGS").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))); //Le cambié la descripción y el color al tag

        //Le aplicamos un zoom hacía al marcador al mapa así se ve más cerca.
        float zoomlevel = 16;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ungs,zoomlevel));

        //El map tiene varios tipos de listeners, le agregamos uno para que cree nuevos marcadores con el longClick (También hay para markers)

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.robo)) //Este es el logo del marker //Acá recuperariamos las posiciones d ela base de datos y las llenariamos con un for.
                    .anchor(0.0f, 1.0f)
                    .position(latLng));

            }
        });

        //Aplicamos un Clicklistener a las marcas que agregamos con el método anterior
        //Acá podemos agregar un menú en otra activity y llamarlo acá.

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(getApplicationContext(), "Robo de bicicleta", Toast.LENGTH_LONG).show();
                return false;
            }
        });

    }
}
