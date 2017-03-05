package com.example.sgarcete.android_maps;

import android.app.Activity;
import android.app.Dialog;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        //Verificamos que los servicios de google maps esten disponibles

        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());

        if (status == ConnectionResult.SUCCESS){

            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);

        }else{
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, (Activity) getApplicationContext(),10);
            dialog.show();
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

    //Este método crea un marcador por default (una ubicación) se puede modificar ;)

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Existen distintos tipos de mapas de google, con esta variable seteamos cual queremos usar
        //Existen los siguientes: Satellite (Satelite google heart, normal: Básica de google map,
        // Hibryd: Combinación entre satelital y normal, None: Mapa sin capas, Terraine: Mapa de terreno)

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

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
    }
}
