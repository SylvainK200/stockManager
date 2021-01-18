package controller;

import Main.Main;
import dao.dao.CategoryDAO;
import dao.dao.FournisseurDAO;
import dao.dao.MaterielDAO;
import dao.daoImpl.CategoryDAOImpl;
import dao.daoImpl.FournisseurDAOImpl;
import dao.daoImpl.MaterielDAOImpl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Category;
import model.Materiel;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PageAjoutMaterielController implements Initializable {
    public static ArrayList<String> categories  = new ArrayList<String>();
    private final FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
    private final CategoryDAO categoryDAO = new CategoryDAOImpl();
    private final MaterielDAO materielDAO = new MaterielDAOImpl();

    @FXML
    private TextField nom;
    @FXML
    private TextField type;

    @FXML
    private ComboBox<Category> categorie;

    @FXML
    private ComboBox<String> nature;

    @FXML
    private Button valider;

    @FXML
    private Button annuler;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCombo();
        //categorie.getItems().addAll(new Category("EPI"),new Category("Maconnerie"),new Category("Menuiserie"),new Category("Ferraillage"),new Category("Peinture"),new Category("Autres"));
        nature.getItems().addAll("Materiau", "Materiel");
    }

    private void setCombo(){
        List<Category> categories = categoryDAO.findAll();
        //List<Fournisseur> fournisseurs = fournisseurDAO.findAll();

        for(Category c: categories){
            categorie.getItems().add(c);
        }
    }

    @FXML
    public void ajout(){
        Materiel materiel = new Materiel(nom.getText(), type.getText(), nature.getSelectionModel().getSelectedItem());
        materiel.setCategory(categorie.getValue());

        materielDAO.save(materiel);

        Main.stage.close();
        Main.showPage("materiel.fxml");
    }

    public void annuler() {
        Main.stage.close();

    }
}
