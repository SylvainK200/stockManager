package model;

import javax.persistence.*;

@Entity
@Table(name = "stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private double quantite;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "stock")
    private Materiel materiel;

    public Stock(){}

    public Stock(double quantite) {
        this.quantite = quantite;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public Materiel getMateriel() {
        return materiel;
    }

    public void setMateriel(Materiel materiel) {
        this.materiel = materiel;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", quantite=" + quantite +
                ", materiel=" + materiel +
                '}';
    }
}
