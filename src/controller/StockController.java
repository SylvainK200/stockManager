package controller;

import Main.Main;
import dao.dao.MaterielDAO;
import dao.dao.OperationDAO;
import dao.dao.PrecommandeDAO;
import dao.dao.StockDAO;
import dao.daoImpl.MaterielDAOImpl;
import dao.daoImpl.OperationDAOImpl;
import dao.daoImpl.PrecommandeDAOImpl;
import dao.daoImpl.StockDAOImpl;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class StockController {

    private final StockDAO stockDAO = new StockDAOImpl();
    private  final PrecommandeDAO precommandeDAO = new PrecommandeDAOImpl();
    private final OperationDAO operationDAO = new OperationDAOImpl();
    private final MaterielDAO materielDAO = new MaterielDAOImpl();

    @FXML
    private TextField rechercher;

    @FXML
    private Pagination pagination;

    @FXML
    private Spinner<Integer> retirerQuantite = new Spinner<Integer>();
    @FXML
    private TableView<Stock1> tableau;

    @FXML
    private TableColumn<Stock1, String> nom;

    @FXML
    private TableColumn<Stock1,String > categorie;

    @FXML
    private TableColumn<Stock1, String> type;

    @FXML
    private TableColumn<Stock1, String> nature;

    @FXML
    private TableColumn<Stock1, Double> Quantite;
    private FilteredList<Stock1> stock1f;

    @FXML
    private Button commander;

    public void commander(){
        Main.showPage("prix_fournisseurs.fxml");
        // on augmenter d'autres fonctionnalites ici
    }
    public void initialize(){
        initTab();
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,50,5) ;
        retirerQuantite.setValueFactory(valueFactory);
    }

    private void initTab(){
        nom.setCellValueFactory(new PropertyValueFactory<Stock1, String>("nom"));
        categorie.setCellValueFactory(new PropertyValueFactory<Stock1, String>("category"));
        type.setCellValueFactory(new PropertyValueFactory<Stock1, String>("type"));
        nature.setCellValueFactory(new PropertyValueFactory<Stock1, String>("nature"));
        Quantite.setCellValueFactory(new PropertyValueFactory<Stock1, Double>("quantite"));
        tableau.setRowFactory(tv -> new TableRow<Stock1>() {
            @Override
            protected void updateItem(Stock1 item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null)
                    setStyle("");
                else if (item.getQuantite() < 5)
                    setStyle("-fx-background-color: rgb(240, 145, 145);");
                else
                    setStyle("");
            }
        });
        TableView.TableViewSelectionModel model = tableau.getSelectionModel();
        model.setSelectionMode(SelectionMode.MULTIPLE);

        ObservableList items = FXCollections.observableArrayList();

        List<Stock> stocks = stockDAO.findAll();
        for(Stock s: stocks){
            items.add(new Stock1(s.getId(), s.getMateriel().getNom(), s.getMateriel().getCategory().getNom(),s.getQuantite(), s.getMateriel().getNature(), s.getMateriel().getType()));
        }

        stock1f = new FilteredList<>(items, p->true);

        //Set the filter Predicate whenever the filter changes.
        rechercher.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            stock1f.setPredicate(stock1->{
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }

                if(stock1.getNom().toLowerCase().contains(newValue.toLowerCase())) return true;

                return false;
            });
            tableau.getItems().clear();
            tableau.getItems().addAll(stock1f);
        });

        tableau.getItems().clear();
        tableau.getItems().addAll(stock1f);
    }

    public void retirerQuantite() {

        if (tableau.getSelectionModel().getSelectedItem() == null) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Absence de selection element");

            // Header Text: null
            alert1.setHeaderText(null);
            alert1.setContentText("Vous n'avez selectionne aucun element dans le tableau ");

            alert1.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous surs de vouloir retirer cette quantite en stock? ", ButtonType.YES, ButtonType.CANCEL);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                Date now = new Date(System.currentTimeMillis());
                Stock1 stock1 = tableau.getSelectionModel().getSelectedItem();
                Precommande precommande = precommandeDAO.findBN(stock1.getNom());
                Operation op = new Operation(precommande.getMateriel().getNom(), "retrait", retirerQuantite.getValue(), precommande.getPrixUnitaire(), now);
                Materiel m = materielDAO.findByName(stock1.getNom());
                op.setMateriel(m);
                operationDAO.save(op);
                Stock stock = stockDAO.find(stock1.getId());
                if (stock.getQuantite() > retirerQuantite.getValue()) {
                    stock.setQuantite(stock.getQuantite() - retirerQuantite.getValue());
                    stockDAO.update(stock);
                }
                Main.showPage("stock.fxml");
            }
        }
    }
}