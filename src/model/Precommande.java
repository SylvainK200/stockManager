package model;

import javax.persistence.*;
import javax.persistence.criteria.Fetch;
import java.util.Date;

@Entity
@Table(name = "precommande")
public class Precommande {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private double prixUnitaire;

    @Temporal(TemporalType.DATE)
    private Date dateDisponibilite;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "materiel_id")
    private Materiel materiel;

    @ManyToOne
    @JoinColumn(name = "fournisseur_id")
    private Fournisseur fournisseur;

    public Precommande(){}

    public Precommande(double prixUnitaire, Date dateDisponibilite) {
        this.prixUnitaire = prixUnitaire;
        this.dateDisponibilite = dateDisponibilite;
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





    public Date getDateDisponibilite() {
        return dateDisponibilite;
    }

    public void setDateDisponibilite(Date dateDisponibilite) {
        this.dateDisponibilite = dateDisponibilite;
    }

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

    @Override
    public String toString() {
        return "Precommande{" +
                "id=" + id +
                ", prixUnitaire=" + prixUnitaire +

                ", dateDisponibilite=" + dateDisponibilite +
                ", materiel=" + materiel +
                ", fournisseur=" + fournisseur +
                '}';
    }
}
