package controller;

import dao.dao.MaterielDAO;
import dao.dao.OperationDAO;
import dao.daoImpl.MaterielDAOImpl;
import dao.daoImpl.OperationDAOImpl;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class JournalController implements Initializable {
    private  OperationDAO operationDAO = new OperationDAOImpl();
    private  MaterielDAO materielDAO = new MaterielDAOImpl();
    @FXML
    private DatePicker dateOperations;
    @FXML
    private TableView<Journal> tableau;

    @FXML
    private TableColumn<Journal, Date> dateOperation;

    @FXML
    private Button rechercher;

    @FXML
    private TableColumn<Journal, String> nom;

    @FXML
    private TableColumn<Journal, String> categorie;

    @FXML
    private TableColumn<Journal, String> type;

    @FXML
    private TableColumn<Journal, Double> prix;

    @FXML
    private TableColumn<Journal, Double> quantite;

    @FXML
    private TableColumn<Journal, String> operation;

    @FXML
    private TableColumn<Journal, String> fournisseur;

    @FXML
    private Button ajouter;

    @FXML
    private Label montant;

    @FXML
    private Button retirer;
    private FilteredList<Journal> journalF;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
    }

    public void filterByDisponibilite(){

        List<Operation> operations = operationDAO.findLDDate(java.sql.Date.valueOf(dateOperations.getValue()));
        tableau.getItems().clear();
        ObservableList items = FXCollections.observableArrayList();
        double somme = 0 ;
        for(Operation op: operations){
            if (op.getFournisseur()!=null){
                items.add(new Journal(op.getMateriel().getId(),op.getMateriel().getNom(),op.getDateOperation(),op.getMateriel().getCategory().getNom(),op.getMateriel().getType(),op.getPrix(),op.getQuantite(),op.getFournisseur().getNom(),op.getTypeOperation()));
                somme+=op.getPrix()*op.getQuantite();
            }
            else
            {
                items.add(new Journal(op.getMateriel().getId(),op.getMateriel().getNom(),op.getDateOperation(),op.getMateriel().getCategory().getNom(),op.getMateriel().getType(),op.getPrix(),op.getQuantite(),"aucun",op.getTypeOperation()));

            }
        }
        tableau.setItems(items);
        montant.setText(Double.toString(somme));
    }


    private void initTable() {
        nom.setCellValueFactory(new PropertyValueFactory<Journal, String>("name"));
        dateOperation.setCellValueFactory(new PropertyValueFactory<Journal, Date>("dateOperation"));
        categorie.setCellValueFactory(new PropertyValueFactory<Journal, String>("category"));
        type.setCellValueFactory(new PropertyValueFactory<Journal, String>("type"));
        operation.setCellValueFactory(new PropertyValueFactory<Journal, String>("operation"));
        fournisseur.setCellValueFactory(new PropertyValueFactory<Journal, String>("fournisseur"));
        prix.setCellValueFactory(new PropertyValueFactory<Journal, Double>("prix"));
        quantite.setCellValueFactory(new PropertyValueFactory<Journal, Double>("quantite"));

        TableView.TableViewSelectionModel model = tableau.getSelectionModel();
        model.setSelectionMode(SelectionMode.MULTIPLE);

        ObservableList items = FXCollections.observableArrayList();


        List<Operation> operations = operationDAO.findAll();
        System.out.println(operations.size());
        List<Materiel> materiels = materielDAO.findAll();
//        for(Materiel m: materiels){
//            System.out.println("materiel : "+m.getNom()+" "+m.getOperations().size());
//            if(m.getOperations() != null){
//                for(Operation o : m.getOperations()){
//                    if (o.getFournisseur() == null)
//                    {
//                        items.add(new Journal(m.getId(), m.getNom(), o.getDateOperation(), m.getCategory().getNom(), m.getType(), o.getPrix(), o.getQuantite(), "aucun", o.getTypeOperation()));
//                    }
//                    else{
//                        items.add(new Journal(m.getId(), m.getNom(), o.getDateOperation(), m.getCategory().getNom(), m.getType(), o.getPrix(), o.getQuantite(), o.getFournisseur().getNom(), o.getTypeOperation()));
//
//                    }
//                           }
//            }
//        }
        double s = 0;
        for (Operation op : operations)
        {
            if (op.getTypeOperation().equals("ajout"))
            {
                s  += op.getPrix()*op.getQuantite();
            }
            if (op.getFournisseur()!=null){
                items.add(new Journal(op.getMateriel().getId(),op.getMateriel().getNom(),op.getDateOperation(),op.getMateriel().getCategory().getNom(),op.getMateriel().getType(),op.getPrix(),op.getQuantite(),op.getFournisseur().getNom(),op.getTypeOperation()));
            }
            else
            {
                items.add(new Journal(op.getMateriel().getId(),op.getMateriel().getNom(),op.getDateOperation(),op.getMateriel().getCategory().getNom(),op.getMateriel().getType(),op.getPrix(),op.getQuantite(),"aucun",op.getTypeOperation()));

            }

        }
        montant.setText(Double.toString(s));
        journalF = new FilteredList<>(items, p -> true);

        //Set the filter Predicate whenever the filter changes.
        rechercher.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            journalF.setPredicate(journ -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                if (journ.getName().toLowerCase().contains(newValue.toLowerCase())) return true;

                return false;
            });
            tableau.getItems().clear();
            tableau.getItems().addAll(journalF);
        });

        tableau.getItems().clear();
        tableau.getItems().addAll(journalF);
    }
        @FXML
    void supprimer(ActionEvent event){
        tableau.getSelectionModel().getSelectedItems().forEach(r ->{
            tableau.getItems().remove(r);

        });
    }

    public void ajouter()
    {

    }

    public void retirer()
    {

    }
}
