package com.example.disfrazitos.Entidades;

public class Disfraz {
    String img_disfraz;
    String nom_disfraz;
    String desc_disfraz;
    String tall_disfraz;
    int cant_disfraz;
    float prec_disfraz;

    public Disfraz() {
    }

    public Disfraz(String img_disfraz, String nom_disfraz, String desc_disfraz, String tall_disfraz, int cant_disfraz, float prec_disfraz) {
        this.img_disfraz = img_disfraz;
        this.nom_disfraz = nom_disfraz;
        this.desc_disfraz = desc_disfraz;
        this.tall_disfraz = tall_disfraz;
        this.cant_disfraz = cant_disfraz;
        this.prec_disfraz = prec_disfraz;
    }

    public String getImg_disfraz() {
        return img_disfraz;
    }

    public void setImg_disfraz(String img_disfraz) {
        this.img_disfraz = img_disfraz;
    }

    public String getNom_disfraz() {
        return nom_disfraz;
    }

    public void setNom_disfraz(String nom_disfraz) {
        this.nom_disfraz = nom_disfraz;
    }

    public String getDesc_disfraz() {
        return desc_disfraz;
    }

    public void setDesc_disfraz(String desc_disfraz) {
        this.desc_disfraz = desc_disfraz;
    }

    public String getTall_disfraz() {
        return tall_disfraz;
    }

    public void setTall_disfraz(String tall_disfraz) {
        this.tall_disfraz = tall_disfraz;
    }

    public int getCant_disfraz() {
        return cant_disfraz;
    }

    public void setCant_disfraz(int cant_disfraz) {
        this.cant_disfraz = cant_disfraz;
    }

    public float getPrec_disfraz() {
        return prec_disfraz;
    }

    public void setPrec_disfraz(float prec_disfraz) {
        this.prec_disfraz = prec_disfraz;
    }


}
