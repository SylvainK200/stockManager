package model;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String username ;
    private String password ;

    public User(){
        this.username = "";
        this.password = "";
    }
    public User(String username,String password){
        this.username =username;
        this.password = password;
    }
    public String getUsername()
    {
        return username;
    }
    public String getPassword(){
        return password;
    }
}
