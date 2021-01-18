package Main;

import dao.dao.CategoryDAO;
import dao.daoImpl.CategoryDAOImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Category;

import java.io.IOException;
import java.util.Date;

public class Main extends Application {
    public static final CategoryDAO categoryDAO = new CategoryDAOImpl();



    public static Stage primaryStage;
    public static BorderPane rootLayout;
    public static Stage  stage = new Stage();
    public static AnchorPane loginAnchor ;

    public static void loginPage(){
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("login.fxml"));
            loginAnchor = (AnchorPane) loader.load();


            // Show the scene containing the root layout.
            Scene scene = new Scene(loginAnchor);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("pagePrincipale.fxml"));
            rootLayout = (BorderPane) loader.load();


            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showPersonOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("journal.fxml"));
            AnchorPane journal = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(journal);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void  showStock()
    {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("stock.fxml"));
            AnchorPane journal = (AnchorPane) loader.load();
            // Set person overview into the center of root layout.
            rootLayout.setCenter(journal);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void showOperation(){
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("journal.fxml"));
            AnchorPane journal = (AnchorPane) loader.load();
            // Set person overview into the center of root layout.
            rootLayout.setCenter(journal);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void start(Stage primaryStage) throws Exception{

            this.primaryStage = primaryStage;
            this.primaryStage.setTitle("Stock Manager");

            //initRootLayout();
            loginPage();
            //showPersonOverview();
    }
    public static void showNewPrixFournisseurs() {

    }

    public static void showPage (String file){
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(""+file));
            AnchorPane journal = (AnchorPane) loader.load();
            // Set person overview into the center of root layout.
            rootLayout.setCenter(journal);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showPages (String page ){

        try {
            Parent root = FXMLLoader.load(Main.class.getResource("" + page));
            //stage.setTitle(titre);
            stage.setScene(new Scene(root));
            stage.showAndWait();
        }catch (Exception e )
        {
            e.printStackTrace();
        }

    }

//
    public static void main(String[] args) {

        Date now = new Date(System.currentTimeMillis());
        System.out.println(now);
        if (categoryDAO.findAll().size() == 0) {
        categoryDAO.save(new Category("EPI"));
        categoryDAO.save(new Category("Maconnerie"));
        categoryDAO.save(new Category("Ferraillage"));
        categoryDAO.save(new Category("Peinture"));
        categoryDAO.save(new Category("autres"));
        categoryDAO.save(new Category("autres"));}
        launch(args);
    }
    public Stage getPrimaryStage() {
        return primaryStage;
    }
}

/*public class Main.Main{

    public static void main(String[] args){
        RoleDAO roleDAO = new RoleDAOImpl();
        Role role = new Role("worker");
        role = roleDAO.save(role);
//        System.out.println(role);
//        roleDAO.delete(151);
        List<Role> roles = roleDAO.findAll();
        for(Role r: roles){
            System.out.println(r);
        }

    }
}*/

