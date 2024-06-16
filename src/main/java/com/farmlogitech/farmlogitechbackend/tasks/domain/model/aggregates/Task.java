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
