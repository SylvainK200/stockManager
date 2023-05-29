package controller;

import Main.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;



import java.net.URL;
import java.util.ResourceBundle;

public class PagePrincipaleController  {

    @FXML
    private Button operations;

    @FXML
    private Button personnel;

    @FXML
    private Button programme;

    @FXML
    private Button fournisseur;

    @FXML
    private Button prixFournisseurs;

    @FXML
    private Button materiel;

    @FXML
    private Button stock;

    @FXML
    private Button statistiques;



    public void initialize(URL location, ResourceBundle resources) {

    }

    public void goToOperation(){


        Main.showPage("journal.fxml");
        fournisseur.setStyle("-fx-background-color : ; ");
        operations.setStyle("-fx-background-color :rgb(143, 155, 231);" +
                "-fx-font-size:20;\n" +
                "   -fx-effect:dropshadow(gaussian, rgb(6, 134, 143),4,0.8,1.0,1.0);\n" +
                "   -fx-text-fill:rgb(255, 255, 255);   ");
        prixFournisseurs.setStyle("-fx-background-color : ; ");
        materiel.setStyle("-fx-background-color : ; ");
        stock.setStyle("-fx-background-color : ; ");

    }

    public void goToFournisseurs(){
        Main.showPage("fournisseurs.fxml");
        fournisseur.setStyle("-fx-background-color : rgb(143, 155, 231) ; " +
                "-fx-font-size:20;\n" +
                "   -fx-effect:dropshadow(gaussian, rgb(6, 134, 143),4,0.8,1.0,1.0);\n" +
                "   -fx-text-fill:rgb(255, 255, 255);  ");
        operations.setStyle("-fx-background-color : ; ");
        prixFournisseurs.setStyle("-fx-background-color : ; ");
        materiel.setStyle("-fx-background-color : ; ");
        stock.setStyle("-fx-background-color : ; ");

    }
    public void goToPrixFournisseurs(){
        Main.showPage("prix_fournisseurs.fxml");
        fournisseur.setStyle("-fx-background-color : ; ");
        operations.setStyle("-fx-background-color : ; ");
        prixFournisseurs.setStyle("-fx-background-color : rgb(143, 155, 231); " +
                "-fx-font-size:20;\n" +
                "   -fx-effect:dropshadow(gaussian, rgb(6, 134, 143),4,0.8,1.0,1.0);\n" +
                "   -fx-text-fill:rgb(255, 255, 255);  ");
        materiel.setStyle("-fx-background-color : ; ");
        stock.setStyle("-fx-background-color : ; ");


    }
    public void goToMaterial (){
        Main.showPage("materiel.fxml");
        fournisseur.setStyle("-fx-background-color : ; ");
        operations.setStyle("-fx-background-color : ; ");
        prixFournisseurs.setStyle("-fx-background-color : ; ");
        materiel.setStyle("-fx-background-color :rgb(143, 155, 231) ;" +
                "-fx-font-size:20;\n" +
                "   -fx-effect:dropshadow(gaussian, rgb(6, 134, 143),4,0.8,1.0,1.0);\n" +
                "   -fx-text-fill:rgb(255, 255, 255);   ");
        stock.setStyle("-fx-background-color : ; ");
    }
    public void goToStock(){
        Main.showStock();
        fournisseur.setStyle("-fx-background-color :  ; ");
        operations.setStyle("-fx-background-color : ; ");
        prixFournisseurs.setStyle("-fx-background-color : ; ");
        materiel.setStyle("-fx-background-color : ; ");
        stock.setStyle("-fx-background-color :rgb(143, 155, 231) ;" +
                "-fx-font-size:20;\n" +
                "   -fx-effect:dropshadow(gaussian, rgb(6, 134, 143),4,0.8,1.0,1.0);\n" +
                "   -fx-text-fill:rgb(255, 255, 255);   ");
    }
    public void goToStatistiques(){

    }
}
