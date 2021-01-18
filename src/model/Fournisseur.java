package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "fournisseur")
public class Fournisseur {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nom;

    private String localisation;

    private String address;

    private String numeroTelephone;

    @OneToMany(mappedBy = "fournisseur")
    private List<Precommande> precommandes;

    @OneToMany(mappedBy = "fournisseur")
    private List<Operation> operations;

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public Fournisseur(){}

    public Fournisseur(String nom, String localisation, String address, String numeroTelephone) {
        this.nom = nom;
        this.localisation = localisation;
        this.address = address;
        this.numeroTelephone = numeroTelephone;
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

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Precommande> getPrecommandes() {
        return precommandes;
    }

    public void setPrecommandes(List<Precommande> precommandes) {
        this.precommandes = precommandes;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    @Override
    public String toString() {
        return nom;
    }
}
