package com.farmlogitech.farmlogitechbackend.tasks.domain.model.aggregates;

import com.farmlogitech.farmlogitechbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.farmlogitech.farmlogitechbackend.tasks.domain.model.commands.commands.CreateTaskCommand;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Task extends AuditableAbstractAggregateRoot<Task> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String status;


    @Column(nullable = false)
    private int timeTask;

    @Column(nullable = false)

    private Long collaboratorId;
    @Column(nullable = false)
    private Long farmerId;
    @Column(nullable = false)
    private String endDate;


    public Task(String description, String status, int time, String endDate, Long collaboratorId, Long farmerId){
        this.description =description;
        this.status = status;
        this.timeTask =time;
        this.collaboratorId = collaboratorId;
        this.farmerId = farmerId;
        this.endDate=endDate;
    }

    public Task(CreateTaskCommand command){
        //Validating description length
        if(command.description().length() >30)
            throw new IllegalArgumentException("Description is too long");

        //Validating not nulls
        if (command.description() == null || command.description().trim().isEmpty() || command.description().length() > 30) {
            throw new IllegalArgumentException("Description cannot be null, empty or more than 30 characters");
        }
        if (command.status() == null || command.status().trim().isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }
        if (command.time() == 0) {
            throw new IllegalArgumentException("Time cannot be 0");
        }
        if (command.collaboratorId() == null) {
            throw new IllegalArgumentException("Collaborator ID cannot be null");
        }
        if (command.farmerId() == null) {
            throw new IllegalArgumentException("Farmer ID cannot be null");
        }
        if (command.endDate() == null || command.endDate().trim().isEmpty()) {
            throw new IllegalArgumentException("End date cannot be null or empty");
        }


        //building
        this.description =  command.description();
        this.status = command.status();
        this.timeTask = command.time();
        this.collaboratorId = command.collaboratorId();
        this.farmerId = command.farmerId();
        this.endDate=command.endDate();
    }
    public Task(){

    }
//    UPDATE THE AGGREGATE
    public void  updateDescription(String description){
        this.description = description;
    }
    public void updateStatus(String status){
        this.status = status;
    }
    public void updateTimeTask(int time ){
        this.timeTask = time;
    }
    public void updateCollaborator(Long idCollaborator){this.collaboratorId = idCollaborator;}





//Try if the endpoint work without this:

    public Long getCollaboratorId() {
        return collaboratorId;
    }

    public Long getFarmerId() {
        return farmerId;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public int getTimeTask() {
        return timeTask;
    }

    public String getEndDate() {
        return endDate;
    }
}
