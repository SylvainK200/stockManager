package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "materiel")
public class Materiel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nom;

    private String type;

    private String nature;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne(mappedBy = "materiel")
    private Stock stock;

    @OneToMany(mappedBy = "materiel")
    private List<Precommande> precommandes;

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    @OneToMany(fetch =FetchType.EAGER, mappedBy = "materiel")
    private List<Operation> operations;

    public Materiel(){}

    public Materiel(String nom, String type, String nature) {
        this.nom = nom;
        this.type = type;
        this.nature = nature;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public List<Precommande> getPrecommandes() {
        return precommandes;
    }

    public void setPrecommandes(List<Precommande> precommandes) {
        this.precommandes = precommandes;
    }

    @Override
    public String toString() {
        return nom;
    }
}
