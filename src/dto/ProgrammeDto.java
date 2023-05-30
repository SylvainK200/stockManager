package dto;

import model.Task;

import java.util.Date;

public class ProgrammeDto {
    private Integer id;
    private String name;
    private String taskDescription;
    private Date realisationDate;
    private Double numberOfHours;
    private String personName;
    private Integer personId;
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

    public static ProgrammeDto map(Task task){
        ProgrammeDto programmeDto = new ProgrammeDto();
        programmeDto.setId(task.getId());
        programmeDto.setName(task.getName());
        programmeDto.setPersonId(task.getPerson().getId());
        programmeDto.setPersonName(task.getPerson().getName());
        programmeDto.setTaskDescription(task.getTaskDescription());
        programmeDto.setNumberOfHours(task.getNumberOfHours());
        programmeDto.setRealisationDate(task.getRealisationDate());
        programmeDto.setPersonPhoneNumber(task.getPerson().getPhoneNumber());
        return programmeDto;
    }
}
