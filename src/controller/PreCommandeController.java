package controller;

import Main.Main;
import dao.dao.*;
import dao.daoImpl.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class PreCommandeController implements Initializable {
    private final StockDAO stockDAO = new StockDAOImpl();
    private final PrecommandeDAOImpl precommandeDAO = new PrecommandeDAOImpl();
    private final OperationDAO operationDAO = new OperationDAOImpl();
    private final MaterielDAO materielDAO = new MaterielDAOImpl();
    private final FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();
    @FXML
    private TextField rechercher;

    @FXML
    private DatePicker dateMaximale;

    @FXML
    private TableView<Precommande1> tableau;

    @FXML
    private TableColumn<Precommande1, String> nom;

    @FXML
    private TableColumn<Precommande1, String> category;

    @FXML
    private TableColumn<Precommande1, String> type;

    @FXML
    private TableColumn<Precommande1, String> nature;

    @FXML
    private TableColumn<Precommande1, Double> prixUnitaire;

    @FXML
    private TableColumn<Precommande1, Date> disponibilite;

    @FXML
    private TableColumn<Precommande1, String> fournisseur;

    @FXML
    private Button prix;
    private FilteredList<Precommande1> precommandeF;


    @FXML
    private Button finddisponibilite;

    @FXML
    private Button ajouter;

    @FXML
    private Button supprimer;

    @FXML
    private Button commander;

    @FXML
    private Spinner<Integer> quantite = new Spinner<Integer>();

    public void filterByPrix(){
        Precommande1 p2 = tableau.getSelectionModel().getSelectedItem();
        ObservableList items = FXCollections.observableArrayList();

        List<Precommande> precommandes= precommandeDAO.filterPrecommandeByPrix(p2.getNom());
        for(Precommande p: precommandes){
            Precommande1 p1 = new Precommande1(p.getId(), p.getPrixUnitaire(), p.getMateriel().getNom(), p.getMateriel().getCategory().getNom(), p.getMateriel().getNature(), p.getMateriel().getType(), p.getFournisseur().getNom(), p.getDateDisponibilite());
            p1.setMateriel(p.getMateriel());
            items.add(p1);
        }
        tableau.setItems(items);
    }
    public void filterByDisponibilite(){
        List<Precommande> precommandes = precommandeDAO.findLDDate(java.sql.Date.valueOf(dateMaximale.getValue()));
        tableau.getItems().clear();
        ObservableList items = FXCollections.observableArrayList();

        for(Precommande p: precommandes){
            Precommande1 p1 = new Precommande1(p.getId(), p.getPrixUnitaire(), p.getMateriel().getNom(), p.getMateriel().getCategory().getNom(), p.getMateriel().getNature(), p.getMateriel().getType(), p.getFournisseur().getNom(), p.getDateDisponibilite());
            p1.setMateriel(p.getMateriel());
            items.add(p1);
        }
        tableau.setItems(items);
    }
    public void ajouter(){
        // ajoute une ligne sur les precommandes
        // ouvre la page ajoutPrecommande
        // je vais faire ca (kouemo)

        Main.showPages("ajoutPrixFournisseur.fxml");

    }
    public void supprimer() {
        if (tableau.getSelectionModel().getSelectedItem() == null) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Absence de selection element");

            // Header Text: null
            alert1.setHeaderText(null);
            alert1.setContentText("Vous n'avez selectionne aucun element dans le tableau ");

            alert1.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous surs de vouloir supprimer ce renseignement?  ", ButtonType.YES, ButtonType.CANCEL);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                tableau.getSelectionModel().getSelectedItems().forEach(r -> {
                    tableau.getItems().remove(r);
                    precommandeDAO.delete(r.getId());
                });
            }

        }
    }
    public void commander(){
        // ici doit creer un objet de type Operation et mettre a jour la quantite du materiel choisi
        // ici on commande une quantite contenue dans le spinner
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous surs de vouloir ajouter cette quantite en stock? ", ButtonType.YES, ButtonType.CANCEL);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            //do stuff
            Precommande1 precommande1 = tableau.getSelectionModel().getSelectedItem();

            Operation op = new Operation(precommande1.getNom(), "ajout", quantite.getValue(), precommande1.getPrixUnitaire(), precommande1.getDisponibilite());
            op.setFournisseur(fournisseurDAO.findByName(precommande1.getFournisseur()));
            op.setMateriel(materielDAO.findByName(precommande1.getNom()));
            Operation op2 = operationDAO.save(op);
            System.out.println(op2.getNom() + " affichage ");

            boolean flag = true;

            List<Stock> stocks = stockDAO.findAll();
            for(Stock s: stocks){
                if (s.getMateriel().getNom().equals(op.getMateriel().getNom()) )
                {
                    s.setQuantite( s.getQuantite()+ quantite.getValue());
                    stockDAO.update(s);
                    flag=false;
                }
            }
            if(flag){
                Stock stock = new Stock(quantite.getValue());
                stock.setMateriel(op.getMateriel());
                stockDAO.save(stock);
            }



        }

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,50,5) ;
        quantite.setValueFactory(valueFactory);
    }

    public void initTable(){
        nom.setCellValueFactory(new PropertyValueFactory<Precommande1, String>("nom"));
        category.setCellValueFactory(new PropertyValueFactory<Precommande1, String>("category"));
        type.setCellValueFactory(new PropertyValueFactory<Precommande1, String>("type"));
        nature.setCellValueFactory(new PropertyValueFactory<Precommande1, String>("nature"));
        prixUnitaire.setCellValueFactory(new PropertyValueFactory<Precommande1,Double>("prixUnitaire"));
        disponibilite.setCellValueFactory(new PropertyValueFactory<Precommande1, Date>("disponibilite"));
        fournisseur.setCellValueFactory(new PropertyValueFactory<Precommande1, String>("fournisseur"));

        TableView.TableViewSelectionModel model = tableau.getSelectionModel();
        model.setSelectionMode(SelectionMode.MULTIPLE);

        List<Precommande> precommandes = precommandeDAO.findAll();

        ObservableList items = FXCollections.observableArrayList();

        for(Precommande p: precommandes){
            if(p.getMateriel() != null ) {
                Materiel materiel = materielDAO.find(p.getMateriel().getId());
                items.add(new Precommande1(p.getId(), p.getPrixUnitaire(), p.getMateriel().getNom(), materiel.getCategory().getNom(), p.getMateriel().getNature(), p.getMateriel().getType(), p.getFournisseur().getNom(), p.getDateDisponibilite()));
            }
        }

        precommandeF = new FilteredList<>(items, p->true);

        //Set the filter Predicate whenever the filter changes.
        rechercher.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            precommandeF.setPredicate(prec->{
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }

                if(prec.getNom().toLowerCase().contains(newValue.toLowerCase())) return true;

                return false;
            });
            tableau.getItems().clear();
            tableau.getItems().addAll(precommandeF);
        });

        tableau.getItems().clear();
        tableau.getItems().addAll(precommandeF);

    }

}
