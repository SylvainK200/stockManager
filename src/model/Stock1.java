package model;

import java.util.Date;

public class Stock1 {

    private int id;

    private String nom;

    private String category;

    private double quantite;

    private String nature;

    private String type;

    public Stock1(int id, String nom, String category, double quantite, String nature, String type) {
        this.id = id;
        this.nom = nom;
        this.category = category;
        this.quantite = quantite;
        this.nature = nature;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
