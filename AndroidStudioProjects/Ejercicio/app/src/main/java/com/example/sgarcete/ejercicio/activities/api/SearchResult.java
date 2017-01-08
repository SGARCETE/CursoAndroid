package com.example.sgarcete.ejercicio.activities.api;

import com.example.sgarcete.ejercicio.activities.model.Article;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;

/**
 * Created by sgarcete on 12/30/16.
 */
public class SearchResult {

    @Expose
    private ArrayList<Article> results = new ArrayList <Article>();

    public ArrayList<Article> getResults() {
        return results;
    }

    public void setResults(ArrayList<Article> results) {
        this.results = results;
    }
}
