package com.example.sgarcete.ejercicio.activities.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sgarcete.ejercicio.R;
import com.example.sgarcete.ejercicio.activities.api.API;
import com.example.sgarcete.ejercicio.activities.model.Article;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private String id;

    //Acá utilizamos la libería butter knife para obtener las vistas y sus items
    @Bind(R.id.image)
    ImageView imagen;

    @Bind(R.id.titulo)
    TextView titulo;

    @Bind(R.id.boton)
    Button boton;

    @Bind(R.id.descripcion)
    TextView descripcion;

    @Bind(R.id.precio)
    TextView precio;

    @Bind(R.id.disponibles)
    TextView disponibles;

    @Bind(R.id.usado)
    TextView usado;

    @Bind(R.id.entrega)
    TextView entrega;

    @Bind(R.id.imageNo)
    ImageView imageNo;

    @Bind(R.id.marca)
    TextView marca;

    @Bind(R.id.color)
    TextView color;

    @Bind(R.id.modelo)
    TextView modelo;

    @Bind(R.id.garantia)
    TextView garantia;

    @Bind(R.id.descripcion5)
    TextView descripcion5;

    //@Bind(R.id.progressBar)
    //ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        descripcion.setVisibility(View.GONE);
        cargarDesdeLaAPI();
        //progressBar.setVisibility(View.GONE); Falta ponerla
    }

    private void cargarDesdeLaAPI() { //Usamos retrofit
        id = getIntent().getStringExtra("id");
        API.getArticle(id, new Callback<Article>() { //Le pasamos el id del artículo y el callback
            @Override
            public void onResponse(Call<Article> call, Response<Article> response) { //Recibió la respuestas
                if (response.isSuccessful()) {
                    Article articuloRecibido = response.body();
                    titulo.setText(articuloRecibido.getTitle());
                    precio.setText("$ " + articuloRecibido.getPrecio());
                    descripcion.setText(articuloRecibido.getTitle());
                    disponibles.setText(articuloRecibido.getDisponible() + "");
                    usado.setText(articuloRecibido.getUsado());
                    getImagen(articuloRecibido);
                    //Seguir agregando más items


                }
            }

            @Override
            public void onFailure(Call<Article> call, Throwable t) { //Falló

            }
        });
    }

    //Configuramos el OnClick usando Butter Knife
    @OnClick(R.id.boton)
    public void onClick(View v) {
        if (descripcion.getVisibility() == View.VISIBLE) {
            descripcion.setVisibility(View.GONE);
        } else {
            descripcion.setVisibility(View.VISIBLE);
        }
    }

    public void getImagen(Article articulo) {
        Picasso.with(this)
                .load(articulo.getPictures().get(0).getUrl())
                .into(imagen);
    }

}
