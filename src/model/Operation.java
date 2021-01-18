package model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "operation")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nom;

    private String typeOperation;

    private double quantite;

    private double prix;

    @Temporal(TemporalType.DATE)
    private Date dateOperation;

    @ManyToOne
    @JoinColumn(name = "materiel_id")
    private Materiel materiel;

    public Materiel getMateriel() {
        return materiel;
    }

    public void setMateriel(Materiel materiel) {
        this.materiel = materiel;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    @ManyToOne
    @JoinColumn(name = "fournisseur_id")
    private Fournisseur fournisseur;

    public Operation(){}

    public Operation(String nom, String typeOperation, double quantite, double prix, Date dateOperation) {
        this.nom = nom;
        this.typeOperation = typeOperation;
        this.quantite = quantite;
        this.prix = prix;
        this.dateOperation = dateOperation;
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

    public String getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(String typeOperation) {
        this.typeOperation = typeOperation;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Date getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(Date dateOperation) {
        this.dateOperation = dateOperation;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", typeOperation=" + typeOperation +
                ", quantite=" + quantite +
                ", prix=" + prix +
                ", dateOperation=" + dateOperation +
                '}';
    }
}
