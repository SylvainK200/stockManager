package controller;

import Main.Main;
import dao.dao.PersonDAO;
import dao.daoImpl.PersonDaoImpl;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Person;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class PersonnelController implements Initializable {
    public final PersonDAO personDao = new PersonDaoImpl();

    @FXML
    private TextField rechercher;

    @FXML
    private Pagination pagination;

    @FXML
    private TableView<Person> tableau;

    @FXML
    private TableColumn<Person, String> name;

    @FXML
    private TableColumn<Person, String> post;

    @FXML
    private TableColumn<Person, String> phoneNumber;

    @FXML
    private TableColumn<Person, Double> remuneration;
    @FXML
    private TableColumn<Person, Date> birthDay;




    @FXML
    private Button supprimer;

    @FXML
    private Button ajouter;
    private FilteredList<Person> personF;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
    }

    public void initTable(){
        name.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        post.setCellValueFactory(new PropertyValueFactory<Person, String>("post"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<Person, String>("phoneNumber"));
        remuneration.setCellValueFactory(new PropertyValueFactory<Person, Double>("remuneration"));
        birthDay.setCellValueFactory(new PropertyValueFactory<Person,Date>("birthDay"));

        TableView.TableViewSelectionModel model = tableau.getSelectionModel();
        model.setSelectionMode(SelectionMode.MULTIPLE);

        List<Person> persons = personDao.findAll();
        ObservableList persons1= FXCollections.observableArrayList(persons);

        personF = new FilteredList<>(persons1, p->true);

        //Set the filter Predicate whenever the filter changes.
        rechercher.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            personF.setPredicate(fourn->{
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }

                if(fourn.getName().toLowerCase().contains(newValue.toLowerCase())) return true;

                return false;
            });
            tableau.getItems().clear();
            tableau.getItems().addAll(personF);
        });

        tableau.getItems().clear();
        tableau.getItems().addAll(personF);
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
                    personDao.delete(r.getId());
                });
            }
        }
    }

    public void ajouter() {
        Main.showPages("pageAjoutPersonnel.fxml");
    }
}
