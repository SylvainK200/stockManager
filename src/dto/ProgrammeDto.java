package dto;

import model.Task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class ProgrammeDto {
    private Integer id;
    private String name;
    private String taskDescription;
    private String realisationDate;
    private Double numberOfHours;
    private String personName;
    private Integer personId;
    private String beginingHour;
    private String endingHour;
    private Double executionPercentage;

    private String personPhoneNumber;

    public String getPersonPhoneNumber() {
        return personPhoneNumber;
    }

    public void setPersonPhoneNumber(String personPhoneNumber) {
        this.personPhoneNumber = personPhoneNumber;
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

    public String getRealisationDate() {
        return realisationDate;
    }

    public void setRealisationDate(String realisationDate) {
        this.realisationDate = realisationDate;
    }

    public Double getNumberOfHours() {
        return numberOfHours;
    }

    public void setNumberOfHours(Double numberOfHours) {
        this.numberOfHours = numberOfHours;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }


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

    public Double getExecutionPercentage() {
        return executionPercentage;
    }

    public void setExecutionPercentage(Double executionPercentage) {
        this.executionPercentage = executionPercentage;
    }

    public static ProgrammeDto map(Task task){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
        String date = dateFormat.format(task.getRealisationDate());
        ProgrammeDto programmeDto = new ProgrammeDto();
        programmeDto.setId(task.getId());
        programmeDto.setName(task.getName());
        programmeDto.setPersonId(task.getPerson().getId());
        programmeDto.setPersonName(task.getPerson().getName());
        programmeDto.setTaskDescription(task.getTaskDescription());
        programmeDto.setNumberOfHours(task.getNumberOfHours());
        programmeDto.setRealisationDate(date);
        programmeDto.setPersonPhoneNumber(task.getPerson().getPhoneNumber());
        programmeDto.setEndingHour(Objects.isNull(task.getEndingHour())?"":task.getEndingHour());
        programmeDto.setBeginingHour(Objects.isNull(task.getBeginingHour())?"":task.getBeginingHour());
        programmeDto.setExecutionPercentage(Objects.isNull(task.getExecutionPercentage())?0d:task.getExecutionPercentage());
        return programmeDto;
    }
}
