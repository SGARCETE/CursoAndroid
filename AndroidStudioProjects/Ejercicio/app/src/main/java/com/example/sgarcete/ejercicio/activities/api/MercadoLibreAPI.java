package com.example.sgarcete.ejercicio.activities.api;


import com.example.sgarcete.ejercicio.activities.model.Article;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MercadoLibreAPI {

    //Lo sacamos de https://api.mercadolibre.com/items/MLA644287324
    @GET("items/{itemId}")
    Call<Article> getArticle(@Path("itemId") String id);

    //https://api.mercadolibre.com/sites/MLA/search?q=lavarropas
    @GET("sites/MLA/search")
    Call<SearchResult> search(@Query("q") String query);

}