package com.example.sgarcete.ejercicio.activities.api;

import com.example.sgarcete.ejercicio.activities.model.Article;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;


import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {

    private static MercadoLibreAPI getAPI() {

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson)) //Le decimos que va a usar GSON
                .baseUrl("https://api.mercadolibre.com/") //Pasamos la URL de la API
                .build();

        MercadoLibreAPI service = retrofit.create(MercadoLibreAPI.class); //Apuntamos a la API de MercadoLibre y lo instanciamos a la interfaz que creamos para recibir el Json

        return service;
    }

    //https://api.mercadolibre.com/items/MLA644287324
    public static void getArticle(String id, Callback<Article> callback) {
        getAPI().getArticle(id).enqueue(callback); //Invocamos el resultado la API
    }

    //https://api.mercadolibre.com/sites/MLA/search?q=lavarropas
    public static void search(String query, Callback<SearchResult> callback) {
        getAPI().search(query).enqueue(callback); //Invocamos el resultado la API
    }
}