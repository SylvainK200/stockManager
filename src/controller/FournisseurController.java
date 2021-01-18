package controller;

import Main.Main;
import dao.dao.FournisseurDAO;
import dao.daoImpl.FournisseurDAOImpl;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Fournisseur;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FournisseurController implements Initializable {

    public final FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();

    @FXML
    private TextField rechercher;

    @FXML
    private Pagination pagination;

    @FXML
    private TableView<Fournisseur> tableau;

    @FXML
    private TableColumn<Fournisseur, String> nom;

    @FXML
    private TableColumn<Fournisseur, String> addresse;

    @FXML
    private TableColumn<Fournisseur, String> localisation;

    @FXML
    private TableColumn<Fournisseur, String> addresseEmail;

    @FXML
    private Button supprimer;

    @FXML
    private Button ajouter;
    private FilteredList<Fournisseur> fournisseursF;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
    }

    public void initTable(){
        nom.setCellValueFactory(new PropertyValueFactory<Fournisseur, String>("nom"));
        addresseEmail.setCellValueFactory(new PropertyValueFactory<Fournisseur, String>("address"));
        localisation.setCellValueFactory(new PropertyValueFactory<Fournisseur, String>("localisation"));
        addresse.setCellValueFactory(new PropertyValueFactory<Fournisseur, String>("numeroTelephone"));

        TableView.TableViewSelectionModel model = tableau.getSelectionModel();
        model.setSelectionMode(SelectionMode.MULTIPLE);

        List<Fournisseur> fournisseurs = fournisseurDAO.findAll();
        ObservableList fournisseurs1= FXCollections.observableArrayList(fournisseurs);

        fournisseursF = new FilteredList<>(fournisseurs1, p->true);

        //Set the filter Predicate whenever the filter changes.
        rechercher.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            fournisseursF.setPredicate(fourn->{
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }

                if(fourn.getNom().toLowerCase().contains(newValue.toLowerCase())) return true;

                return false;
            });
            tableau.getItems().clear();
            tableau.getItems().addAll(fournisseursF);
        });

        tableau.getItems().clear();
        tableau.getItems().addAll(fournisseursF);
    }

    @FXML
    void supprimer() {
        if (tableau.getSelectionModel().getSelectedItem() == null) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Absence de selection element");

            // Header Text: null
            alert1.setHeaderText(null);
            alert1.setContentText("Vous n'avez selectionne aucun element dans le tableau ");

            alert1.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous surs de vouloir supprimer un fournisseur ?  ", ButtonType.YES, ButtonType.CANCEL);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                tableau.getSelectionModel().getSelectedItems().forEach(r -> {
                    tableau.getItems().remove(r);
                    fournisseurDAO.delete(r.getId());
                });
            }
        }
    }

    public void ajouter() {
        Main.showPages("pageAjoutFournisseur.fxml");
    }
}
