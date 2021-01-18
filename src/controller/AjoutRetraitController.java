package controller;


import dao.dao.FournisseurDAO;
import dao.daoImpl.FournisseurDAOImpl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import model.Fournisseur;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AjoutRetraitController implements Initializable {

    private final FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();

    @FXML
    private Label intitule;

    @FXML
    private TextField nom;

    @FXML
    private ComboBox<String> fournisseur;

    @FXML
    private Spinner<?> quantite;

    @FXML
    private Button valider;

    @FXML
    private Button annuler;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadFournisseuers();

    }

    private void loadFournisseuers(){
        List<Fournisseur> fournisseurs = fournisseurDAO.findAll();
        List<String> fourns = new ArrayList<String>();
        fournisseurs.forEach(f -> {
            fourns.add(f.getNom());
        });
    }

    public void valider()
    {

    }

    public void annuler()
    {

    }
}
