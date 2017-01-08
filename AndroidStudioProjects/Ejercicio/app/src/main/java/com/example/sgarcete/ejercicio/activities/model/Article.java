package com.example.sgarcete.ejercicio.activities.model;

import android.widget.ImageView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.Table;

import java.util.ArrayList;

/**
 * Created by sgarcete on 12/27/16.
 */

public class Article extends SugarRecord { //Usnado SugarRecord le decimos que persista la clase

    //Json: https://api.mercadolibre.com/items/MLA644287324
    @Expose
    private ArrayList<Picture> pictures = new ArrayList<Picture>(); //La api devuelve un array de fotos

    private Long id;

    @Expose
    @SerializedName("id")
    private String id_server;

    @Expose //los atributos que vienen de la api y queremos persisitir tienen Expose (para evitar guardar el id)
    private String title;

    @Expose
    @SerializedName("available_quantity")
    private int disponible;

    @Expose
    @SerializedName("condition")
    private String usado;

    @Expose
    @SerializedName("price")
    private Double precio;

    @Expose
    private String thumbnail;

    private String textoBuscado;

    //@SerializedName("warranty")
    //private String garantia;
//    @SerializedName("price")
//    private String entrega;
//    @SerializedName("price")
//    private String marca;
//    @SerializedName("price")
//    private String color;
//    @SerializedName("price")
//    private String modelo;
//    @SerializedName("price")
//    private String garantia;
//    @SerializedName("price")
//    private String descripcion;


    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getPrecio() {
        return precio;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDisponible() {
        return disponible;
    }

    public void setDisponible(int disponible) {
        this.disponible = disponible;
    }

    public String getUsado() {
        return usado;
    }

    public void setUsado(String usado) {
        this.usado = usado;
    }


    public ArrayList<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(ArrayList<Picture> pictures) {
        this.pictures = pictures;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getIdServer() {
        return id_server;
    }

    public void setIdServer(String id) {
        this.id_server = id;
    }

    public void setTextoBuscado(String textoBuscado) {
        this.textoBuscado = textoBuscado;
    }

    public String getTextoBuscado() {
        return textoBuscado;
    }

    //    public String getDescripcion() {
//        return descripcion;
//    }
//
//    public void setDescripcion(String descripcion) {
//        this.descripcion = descripcion;
//    }
//
//    public String getGarantia() {
//        return garantia;
//    }
//
//    public void setGarantia(String garantia) {
//        this.garantia = garantia;
//    }
//
//    public String getModelo() {
//        return modelo;
//    }
//
//    public void setModelo(String modelo) {
//        this.modelo = modelo;
//    }
//
//    public String getColor() {
//        return color;
//    }
//
//    public void setColor(String color) {
//        this.color = color;
//    }
//
//    public String getMarca() {
//        return marca;
//    }
//
//    public void setMarca(String marca) {
//        this.marca = marca;
//    }
//
//    public String getEntrega() {
//        return entrega;
//    }
//
//    public void setEntrega(String entrega) {
//        this.entrega = entrega;
//    }
}
