package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nom;

    @OneToMany(mappedBy = "category")
    private Set<Materiel> materiels = new HashSet<Materiel>();

    public Category(){}

    public Category(String nom) {
        this.nom = nom;
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

    public Set<Materiel> getMateriels() {
        return materiels;
    }

    public void setMateriels(Set<Materiel> materiels) {
        this.materiels = materiels;
    }

    @Override
    public String toString() {
        return nom;
    }
}
