package com.example.disfrazitos.Entidades;

public class Categorias {
    String id_categoria;
    String nom_categoria;
    String img_categoria;

    public Categorias() {
    }

    public Categorias(String id_categoria, String nom_categoria, String img_categoria) {
        this.id_categoria = id_categoria;
        this.nom_categoria = nom_categoria;
        this.img_categoria = img_categoria;
    }

    public String getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(String id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNom_categoria() {
        return nom_categoria;
    }

    public void setNom_categoria(String nom_categoria) {
        this.nom_categoria = nom_categoria;
    }

    public String getImg_categoria() {
        return img_categoria;
    }

    public void setImg_categoria(String img_categoria) {
        this.img_categoria = img_categoria;
    }
}
