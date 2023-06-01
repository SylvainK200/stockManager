package model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String taskDescription;
    @Temporal(TemporalType.DATE)
    private Date realisationDate;

    private Double numberOfHours;

    private String beginingHour;

    private String endingHour;

    private Double executionPercentage;

    public String getBeginingHour() {
        return beginingHour;
    }

    public void setBeginingHour(String beginingHour) {
        this.beginingHour = beginingHour;
    }

    public String getEndingHour() {
        return endingHour;
    }

    public void setEndingHour(String endingHour) {
        this.endingHour = endingHour;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private Person person;

    public Task(Integer id, String name, String taskDescription, Date realisationDate, Double numberOfHours, Person person) {
        this.id = id;
        this.name = name;
        this.taskDescription = taskDescription;
        this.realisationDate = realisationDate;
        this.numberOfHours = numberOfHours;

        this.person = person;
    }

    public Task(Integer id, String name, String taskDescription,
                Date realisationDate, Double numberOfHours,
                String beginingHour, String endingHour,
                Double executionPercentage, Person person) {
        this.id = id;
        this.name = name;
        this.taskDescription = taskDescription;
        this.realisationDate = realisationDate;
        this.numberOfHours = numberOfHours;
        this.beginingHour = beginingHour;
        this.endingHour = endingHour;
        this.executionPercentage = executionPercentage;
        this.person = person;
    }

    public Task(){}



    public Double getExecutionPercentage() {
        return executionPercentage;
    }

    public void setExecutionPercentage(Double executionPercentage) {
        this.executionPercentage = executionPercentage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Date getRealisationDate() {
        return realisationDate;
    }

    public void setRealisationDate(Date realisationDate) {
        this.realisationDate = realisationDate;
    }

    public Double getNumberOfHours() {
        return numberOfHours;
    }

    public void setNumberOfHours(Double numberOfHours) {
        this.numberOfHours = numberOfHours;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
