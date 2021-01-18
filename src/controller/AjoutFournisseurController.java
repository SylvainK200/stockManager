package controller;

import Main.Main;
import dao.dao.FournisseurDAO;
import dao.daoImpl.FournisseurDAOImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Fournisseur;

public class AjoutFournisseurController {

    private final FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();

    @FXML
    private TextField nom;

    @FXML
    private TextField localisation;

    @FXML
    private TextField addresseEmail;

    @FXML
    private TextField numeroTelephone;

    @FXML
    private Button valider;

    @FXML
    private Button annuler;


    @FXML
    void ajouter(){
        if(infoBon()) {
            Fournisseur fournisseur = new Fournisseur(nom.getText(), localisation.getText(), addresseEmail.getText(), numeroTelephone.getText());
            fournisseurDAO.save(fournisseur);
        }
        Main.stage.close();
        Main.showPage("fournisseurs.fxml");
    }

    private boolean infoBon(){
        if(nom.getText() == null || nom.getText().equals("")) return false;
        if(localisation.getText() == null || localisation.getText().equals("")) return false;
        if(addresseEmail.getText() == null || addresseEmail.getText().equals("")) return false;
        if(numeroTelephone.getText() == null || numeroTelephone.getText().equals("")) return false;
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
