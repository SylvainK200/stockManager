package model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nom;
    private String post;
    private String phoneNumber;
    private Double remuneration;
    @Temporal(TemporalType.DATE)
    private Date birthDay;

    public Person(Integer id, String nom, String post, String phoneNumber, Double remuneration, Date birthDay) {
        this.id = id;
        this.nom = nom;
        this.post = post;
        this.phoneNumber = phoneNumber;
        this.remuneration = remuneration;
        this.birthDay = birthDay;
    }
    public Person(){}
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return nom;
    }

    public void setName(String nom) {
        this.nom = nom;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Double getRemuneration() {
        return remuneration;
    }

    public void setRemuneration(Double remuneration) {
        this.remuneration = remuneration;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
}
