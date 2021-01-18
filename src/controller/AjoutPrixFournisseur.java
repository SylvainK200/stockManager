package controller;

import Main.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;


public class AjoutPrixFournisseur {

    @FXML
    private TextField materiel;

    @FXML
    private DatePicker disponibilite;

    @FXML
    private TextField prix;

    @FXML
    private TextField fournisseur;

    @FXML
    private Button valider;

    @FXML
    private Button annuler;

    public void initialize(){

    }

    public void valider(){

    }

    public void annuler(){
        Main.stage.close();
        
    }
}
