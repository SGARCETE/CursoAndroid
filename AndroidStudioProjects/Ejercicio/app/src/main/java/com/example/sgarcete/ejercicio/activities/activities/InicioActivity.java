package com.example.sgarcete.ejercicio.activities.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.crashlytics.android.Crashlytics;
import com.example.sgarcete.ejercicio.R;
import com.example.sgarcete.ejercicio.activities.api.API;
import com.example.sgarcete.ejercicio.activities.api.SearchResult;
import com.example.sgarcete.ejercicio.activities.model.Article;
import com.squareup.picasso.Picasso;

import io.fabric.sdk.android.Fabric;
import java.io.Serializable;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InicioActivity extends AppCompatActivity {

    @Bind(R.id.botonInicio)
    Button boton;

    @Bind(R.id.imagen)
    ImageView imagen;

    @Bind(R.id.texto)
    EditText campoDeTexto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_inicio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);


        //Usamos preferencias
        SharedPreferences preferencias = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        boolean tieneNotificaciones = preferencias.getBoolean("notificacionesActivas", false);

        //......

        SharedPreferences.Editor editor = getSharedPreferences("preferencias", Context.MODE_PRIVATE)
                .edit();
        editor.putBoolean("playstation 4", true);
        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Picasso.with(this)
        //        .setIndicatorsEnabled(true);
        //Cambiamos el contenido de la imagen con Picasso
        Picasso.with(this)
                .load("http://cdn01.ib.infobae.com/adjuntos/162/imagenes/011/356/0011356339.jpg")
                .into(imagen);
    }

    @OnClick(R.id.botonInicio)
    public void onClick(View v) {
        Intent intent = new Intent(InicioActivity.this, ResultActivity.class);
        intent.putExtra("termino", campoDeTexto.getText().toString());
        startActivity(intent);


    }

}
