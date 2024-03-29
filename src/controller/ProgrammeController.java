package controller;

import Main.Main;
import dao.dao.PersonDAO;
import dao.dao.TaskDAO;
import dao.daoImpl.PersonDaoImpl;
import dao.daoImpl.TaskDaoImpl;
import dto.ProgrammeDto;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Task;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ProgrammeController implements Initializable {
    public final TaskDAO taskDAO = new TaskDaoImpl();
    public final PersonDAO personDAO = new PersonDaoImpl();

    @FXML
    private TextField rechercher;

    @FXML
    private Pagination pagination;

    @FXML
    private TableView<ProgrammeDto>tableau;

    @FXML
    private TableColumn<ProgrammeDto,String> personName;

    @FXML
    private TableColumn<ProgrammeDto,String> taskDescription;

    @FXML
    private TableColumn<ProgrammeDto, String> phoneNumber;

    @FXML
    private TableColumn<ProgrammeDto, String> realisationDate;
    @FXML
    private TableColumn<ProgrammeDto,Double> numberOfHours;

    @FXML
    private TableColumn<ProgrammeDto, String> beginingDate;

    @FXML
    private TableColumn<ProgrammeDto, String> endingDate;

    @FXML
    private TableColumn<ProgrammeDto, String> executionPercentage;

    @FXML
    private TextField effectiveBeginingHour;

    @FXML
    private TextField effectiveEndingHour;

    @FXML
    private TextField percentage;

    @FXML
    private Button registerButtonRealisation;



    @FXML
    private Button supprimer;

    @FXML
    private Button ajouter;
    private FilteredList<ProgrammeDto> programF;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initTable();
    }

    public void initTable(){
        personName.setCellValueFactory(new PropertyValueFactory<ProgrammeDto, String>("personName"));
        taskDescription.setCellValueFactory(new PropertyValueFactory<ProgrammeDto, String>("taskDescription"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<ProgrammeDto, String>("personPhoneNumber"));
        numberOfHours.setCellValueFactory(new PropertyValueFactory<ProgrammeDto, Double>("numberOfHours"));
        realisationDate.setCellValueFactory(new PropertyValueFactory<ProgrammeDto, String>("realisationDate"));
        endingDate.setCellValueFactory(new PropertyValueFactory<ProgrammeDto,String>("endingDate"));
        beginingDate.setCellValueFactory(new PropertyValueFactory<ProgrammeDto,String>("beginingDate"));
        executionPercentage.setCellValueFactory(new PropertyValueFactory<ProgrammeDto,String>("executionPercentage"));

        TableView.TableViewSelectionModel<ProgrammeDto> model = tableau.getSelectionModel();
        model.setSelectionMode(SelectionMode.MULTIPLE);

        List<ProgrammeDto> programs = taskDAO.findAll().stream().map(ProgrammeDto::map).collect(Collectors.toList());
        ObservableList programs1= FXCollections.observableArrayList(programs);

        programF = new FilteredList<>(programs1, p->true);

        //Set the filter Predicate whenever the filter changes.
        rechercher.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            programF.setPredicate(fourn->{
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }

                if(fourn.getPersonName().toLowerCase().contains(newValue.toLowerCase())) return true;

                return false;
            });
            tableau.getItems().clear();
            tableau.getItems().addAll(programF);
        });

        tableau.getItems().clear();
        tableau.getItems().addAll(programF);
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
                    taskDAO.delete(r.getId());
                });
            }
        }
    }

    public void ajouter() {
        Main.showPages("ajoutProgramme.fxml");
    }
    @FXML
    void ajouterRealisation() {
        ProgrammeDto programmeDto = tableau.getSelectionModel().getSelectedItem();
        if(Objects.nonNull(programmeDto) && infoBon()){
            Task task = taskDAO.find(programmeDto.getId());
            task.setBeginingHour(effectiveBeginingHour.getText());
            task.setEndingHour(effectiveEndingHour.getText());
            task.setExecutionPercentage(Double.parseDouble(percentage.getText()));
            taskDAO.save(task);
            initTable();
        }

    }
    boolean infoBon(){
        return Objects.nonNull(effectiveBeginingHour.getText()) && effectiveBeginingHour.getText().length()>0
                && Objects.nonNull(effectiveEndingHour.getText()) && effectiveEndingHour.getText().length()>0
                && Objects.nonNull(percentage.getText())
                && percentage.getText().length()>0;
    }
}
