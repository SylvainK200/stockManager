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
    private static  String selectedStyleButton = "-fx-background-color :rgb(143, 155, 231);" +
            "-fx-font-size:20;\n" +
            "   -fx-effect:dropshadow(gaussian, rgb(6, 134, 143),4,0.8,1.0,1.0);\n" +
            "   -fx-text-fill:rgb(255, 255, 255);   ";
    private static  String unselectedButton = "-fx-background-color : ; ";

    public void initStyle(){
        fournisseur.setStyle(unselectedButton);
        operations.setStyle(unselectedButton);
        prixFournisseurs.setStyle(unselectedButton);
        materiel.setStyle(unselectedButton);
        stock.setStyle(unselectedButton);
        personnel.setStyle(unselectedButton);
        programme.setStyle(unselectedButton);
    }

    public void goToOperation(){
        Main.showPage("journal.fxml");
        initStyle();
        operations.setStyle(selectedStyleButton);

    }

    public void goToFournisseurs(){
        Main.showPage("fournisseurs.fxml");
        initStyle();
        fournisseur.setStyle(selectedStyleButton);
    }
    public void goToPrixFournisseurs(){
        Main.showPage("prix_fournisseurs.fxml");
        initStyle();
        prixFournisseurs.setStyle(selectedStyleButton);
    }
    public void goToMaterial (){
        Main.showPage("materiel.fxml");
        initStyle();
        materiel.setStyle(selectedStyleButton);
    }
    public void goToStock(){
        Main.showStock();
        initStyle();
        stock.setStyle(selectedStyleButton);
    }
    public void goToPersonnel(){
        Main.showPersonnel();
        initStyle();
        personnel.setStyle(selectedStyleButton);
    }

    public void goToProgramme(){
        Main.showProgramme();
        initStyle();
        programme.setStyle(selectedStyleButton);
    }
    public void goToStatistiques(){

    }
}
