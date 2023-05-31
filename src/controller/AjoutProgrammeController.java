package controller;

import Main.Main;
import dao.dao.PersonDAO;
import dao.dao.TaskDAO;
import dao.daoImpl.PersonDaoImpl;
import dao.daoImpl.TaskDaoImpl;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Person;
import model.Task;

import javax.swing.*;
import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AjoutProgrammeController {
    TaskDAO taskDAO = new TaskDaoImpl();
    PersonDAO personDAO = new PersonDaoImpl();
    @FXML
    private TextField personName;

    @FXML
    private DatePicker realisationDate;

    @FXML
    private TextField taskDescription;

    @FXML
    private TextField numberOfHours;

    @FXML
    private Button valider;

    @FXML
    private Button annuler;


    @FXML
    void ajouter(){
        if(infoBon()) {
            Person person = personDAO.findByName(personName.getText());
            Task task= new Task(null,taskDescription.getText(),
                    taskDescription.getText(),
                    Date.valueOf(realisationDate.getValue()),
                    Double.parseDouble(numberOfHours.getText()),
                    person);
            taskDAO.save(task);
            Main.stage.close();
            Main.showPage("programme.fxml");
        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Veuillez bien remplir les informations ", ButtonType.YES, ButtonType.CANCEL);
            alert.showAndWait();
        }

    }

    private boolean infoBon(){
        if(personName.getText() == null || personName.getText().equals("")) return false;
        if(taskDescription.getText() == null || taskDescription.getText().equals("")) return false;
        if(realisationDate.getValue() == null) return false;
        if(numberOfHours.getText() == null ||
                numberOfHours.getText().equals("")) return false;
        return true;
    }
    public void  valider()
    {

    }

    public void annuler()
    {
        Main.stage.close();
    }

}
