package com.example.sgarcete.ejercicio.activities.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.sgarcete.ejercicio.R;
import com.example.sgarcete.ejercicio.activities.adapters.ArticlesAdapter;
import com.example.sgarcete.ejercicio.activities.api.API;
import com.example.sgarcete.ejercicio.activities.api.SearchResult;
import com.example.sgarcete.ejercicio.activities.model.Article;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArticlesAdapter articlesAdapter;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private String textoABuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.recyclerView = (RecyclerView) findViewById(R.id.articles);
        this.layoutManager = new LinearLayoutManager(this);

        this.recyclerView.setLayoutManager(this.layoutManager);

        this.articlesAdapter = new ArticlesAdapter();
        this.recyclerView.setAdapter(this.articlesAdapter);

        textoABuscar = getIntent().getStringExtra("termino"); //Le pasamos la palabra a buscar a la próxima ventana, así cargamos datos de este artículo.

        //Usando preferencias, guardamos las palabras que ya buscamos, si ya la buscamos cargamos la vista desde base de datos, sino la obtenemos desde la api y la persistimos
        SharedPreferences preferencias = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        boolean seBuscoAntes = preferencias.getBoolean(textoABuscar, false);

        if (seBuscoAntes) {
            cargarDesdeBaseDeDatos();
        } else {
            cargarDesdeLaAPI();
        }


    }

    private void cargarDesdeBaseDeDatos() {
        List<Article> article = Article.find(Article.class, "texto_buscado = ?", textoABuscar);
        articlesAdapter.setItems(article);
    }


    private void cargarDesdeLaAPI() {

        API.search(textoABuscar, new Callback<SearchResult>() { //Le pasamos el id del artículo y el callback
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) { //Recibió la respuesta
                if (response.isSuccessful()) {
                    SearchResult searchResult = response.body(); //Obtenemos la lista de la api
                    articlesAdapter.setItems(searchResult.getResults()); //Se la pasamos al adapter del recycle view

                    SharedPreferences.Editor editor = getSharedPreferences("preferencias", Context.MODE_PRIVATE).edit();
                    editor.putBoolean(textoABuscar, true); //Lo ponemos en true (ya lo buscamos, la próxima vez tenemos que sacarlo de la base)
                    editor.commit();

                    for (Article article : searchResult.getResults()) {
                        article.setTextoBuscado(textoABuscar); //Le decimos al articulo cuanl es su texto buscado.
                        article.save(); //Lo guardamos en la base de datos. Tiene que extender .sugar y tener los exposed en los atributos que vienen de la api.
                    }
                }
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) { //Falló
            }
        });
    }

}
