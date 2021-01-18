package model;

import java.util.Date;

public class Precommande1 {

    private Integer id;

    private double prixUnitaire;

    private double quantite;

    private String nom;

    private String category;

    private String nature;

    private String type;

    private String fournisseur;

    private Date disponibilite;

    private Materiel materiel;

    public Materiel getMateriel() {
        return materiel;
    }

    public void setMateriel(Materiel materiel) {
        this.materiel = materiel;
    }

    public Precommande1(Integer id, double prixUnitaire, String nom, String category, String nature, String type, String fournisseur, Date disponibilite) {
        this.id = id;
        this.prixUnitaire = prixUnitaire;

        this.nom = nom;
        this.category = category;
        this.nature = nature;
        this.type = type;
        this.fournisseur = fournisseur;
        this.disponibilite = disponibilite;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
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

    public String getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }

    public Date getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(Date disponibilite) {
        this.disponibilite = disponibilite;
    }
}
