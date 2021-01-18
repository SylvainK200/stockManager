package controller;

import Main.Main;
import dao.dao.UserDAO;
import dao.daoImpl.UserDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.User;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController {
    private final UserDAO userDAO = new UserDAOImpl();

    @FXML
    private TextField username;

    @FXML
    private Button connect;

    @FXML
    private Button annuler;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField confirmation ;
    public void initialize() {
            if (userDAO.findAll().size()>0) {
                confirmation.setVisible(false);
            }
    }
    public void connect() {
        String usern = username.getText();
        String passwords = password.getText();
        String confirms  = confirmation.getText();

        if (userDAO.findAll().size() > 0) {
            confirmation.setVisible(false);
            System.out.println("sjlkdf jlj ");
            User user = userDAO.findAll().get(0);

            if (usern.equals(user.getUsername()) && passwords.equals(user.getPassword())) {
                Main.initRootLayout();
                Main.showPage("journal.fxml");
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erreur connection");

                // Header Text: null
                alert.setHeaderText(null);
                alert.setContentText("Votre username ou votre mot de passe ne correspond pas ");

                alert.showAndWait();
            }

        }
        else
        {
            if (confirms.equals(passwords)){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous surs de pouvoir vous rappeler de votre mot de passe ? \n " +
                        "celui -ci ne sera plus modifiable !  ", ButtonType.YES, ButtonType.CANCEL);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                    User user = new User(usern,passwords);
                    userDAO.save(user);
                    Main.initRootLayout();
                    Main.showPage("journal.fxml");
                }
                else if (alert.getResult()==ButtonType.CANCEL) {
                    confirmation.setText(null);
                    password.setText(null);
                    username.setText(null);
                }
                }
            }
        }
    }

