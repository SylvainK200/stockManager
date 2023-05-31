package controller;

import Main.Main;
import dao.dao.PersonDAO;
import dao.daoImpl.PersonDaoImpl;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Fournisseur;
import model.Person;

import javax.swing.*;
import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AjoutPersonnelController {
    private final PersonDAO personDAO = new PersonDaoImpl();

    @FXML
    private TextField name;

    @FXML
    private TextField post;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField remuneration;


    @FXML
    private DatePicker birthDay;

    @FXML
    private Button valider;

    @FXML
    private Button annuler;


    @FXML
    void ajouter(){
        if(infoBon()) {
            System.out.println("Is bon");
            Person person = new Person(null,name.getText(),
                    post.getText(),phoneNumber.getText(),
                    Double.parseDouble(remuneration.getText()),
                    Date.valueOf(birthDay.getValue()));
            personDAO.save(person);
            Main.stage.close();
            Main.showPage("personnel.fxml");
        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Veuillez bien remplir les informations ", ButtonType.YES, ButtonType.CANCEL);
            alert.showAndWait();
        }
    }

    private boolean infoBon(){
        if(name.getText() == null || name.getText().equals("")) return false;
        if(post.getText() == null || post.getText().equals("")) return false;
        if(phoneNumber.getText() == null
                || phoneNumber.getText().equals("")) return false;
        if(remuneration.getText() == null ||
                remuneration.getText().equals("")) return false;
        return true;
    }
    public void  valider()
    {

    }

    public void annuler()
    {
        Main.stage.close();
    }

}
