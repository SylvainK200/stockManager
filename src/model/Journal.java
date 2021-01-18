package model;

import java.util.Date;

public class Journal {

    private int id;

    private String name;

    private Date dateOperation;

    private String category;

    private String type;

    private String operation;

    private double prix;

    private double quantite;

    private String fournisseur;

    public Journal(int id, String name, Date dateOperation, String category, String type, double prix, double quantite, String fournisseur, String operation) {
        this.id = id;
        this.name = name;
        this.dateOperation = dateOperation;
        this.category = category;
        this.type = type;
        this.prix = prix;
        this.quantite = quantite;
        this.fournisseur = fournisseur;
        this.operation = operation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public String getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }

    public Date getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(Date dateOperation) {
        this.dateOperation = dateOperation;
    }
}
