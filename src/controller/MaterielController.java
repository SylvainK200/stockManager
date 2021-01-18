package controller;

import Main.Main;

import dao.dao.FournisseurDAO;
import dao.dao.MaterielDAO;
import dao.dao.PrecommandeDAO;

import dao.daoImpl.FournisseurDAOImpl;
import dao.daoImpl.MaterielDAOImpl;
import dao.daoImpl.PrecommandeDAOImpl;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Category;
import model.Materiel;
import model.Precommande;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MaterielController implements Initializable {

    private final MaterielDAO materielDAO = new MaterielDAOImpl();
    private final PrecommandeDAO precommandeDAO = new PrecommandeDAOImpl();
    private final FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();

    @FXML
    private Button creerPreCommande;

    @FXML
    private TextField prix;

    @FXML
    private TextField nomFournisseur;

    @FXML
    private DatePicker disponibilite;

    @FXML
    private TextField rechercher;

    @FXML
    private TableView<Materiel> tableau;

    @FXML
    private TableColumn<Materiel, String> nom;

    @FXML
    private TableColumn<Materiel, Category> categorie;

    @FXML
    private TableColumn<Materiel, String> type;

    @FXML
    private TableColumn<Materiel, String> nature;

    @FXML
    private Button Supprimer;

    @FXML
    private Button ajouter;
    private FilteredList<Materiel> materielF;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
    }

    public void initTable(){
        nom.setCellValueFactory(new PropertyValueFactory<Materiel, String>("nom"));
        categorie.setCellValueFactory(new PropertyValueFactory<Materiel, Category>("category"));
        type.setCellValueFactory(new PropertyValueFactory<Materiel, String>("type"));
        nature.setCellValueFactory(new PropertyValueFactory<Materiel, String>("nature"));

        TableView.TableViewSelectionModel model = tableau.getSelectionModel();
        model.setSelectionMode(SelectionMode.MULTIPLE);

        List<Materiel> materiels = materielDAO.findAll();

        ObservableList items = FXCollections.observableArrayList(materiels);


        materielF = new FilteredList<>(items, p->true);

        //Set the filter Predicate whenever the filter changes.
        rechercher.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            materielF.setPredicate(mat->{
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }

                if(mat.getNom().toLowerCase().contains(newValue.toLowerCase()) || mat.getCategory().getNom().toLowerCase().contains(newValue.toLowerCase())

                || mat.getNature().toLowerCase().contains(newValue.toLowerCase())) return true;

                return false;
            });
            tableau.getItems().clear();
            tableau.getItems().addAll(materielF);
        });

        tableau.getItems().clear();
        tableau.getItems().addAll(materielF);
    }

    @FXML
    public void supprimer(ActionEvent event) {
        if (tableau.getSelectionModel().getSelectedItem() == null) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Absence de selection element");

            // Header Text: null
            alert1.setHeaderText(null);
            alert1.setContentText("Vous n'avez selectionne aucun element dans le tableau ");

            alert1.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous surs de vouloir supprimer ce materiel ?  ", ButtonType.YES, ButtonType.CANCEL);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                tableau.getSelectionModel().getSelectedItems().forEach(r -> {
                    tableau.getItems().remove(r);
                    materielDAO.delete(r.getId());
                });
            }
        }

    }

    public void ajouter(){
        // ouvre la page ajoutMateriel
        Main.showPages("pageAjoutMateriel.fxml");

    }


    public void createPrecommande() {
        Materiel m = tableau.getSelectionModel().getSelectedItem();
        if(m != null){
            Precommande p = new Precommande(Double.parseDouble(prix.getText()), Date.valueOf(disponibilite.getValue()));
            p.setMateriel(materielDAO.find(m.getId()));
            System.out.println(m.getCategory());
            p.setFournisseur(fournisseurDAO.findByName(nomFournisseur.getText()));
            precommandeDAO.save(p);
        // creer une precommande en entrant
    }
}
}