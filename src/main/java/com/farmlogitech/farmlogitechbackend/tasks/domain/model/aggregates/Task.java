package com.farmlogitech.farmlogitechbackend.tasks.domain.model.aggregates;

import com.farmlogitech.farmlogitechbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.farmlogitech.farmlogitechbackend.tasks.domain.model.commands.commands.CreateTaskCommand;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Entity
@Builder
@EntityListeners(AuditingEntityListener.class)
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
    private Date endDate;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    public Task(String description, String status, int time, Date endDate, Long collaboratorId, Long farmerId){
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

        //Validating not nulls and showing  error in console
        if (command.description() == null || command.description().trim().isEmpty() || command.description().length() > 30) {
            throw new IllegalArgumentException("Description cannot be null, empty or more than 30 characters");
        }
        if (command.time() == 0) {
            throw new IllegalArgumentException("Time cannot be 0");
        }
        if (command.collaboratorId() == null) {
            throw new IllegalArgumentException("Collaborator ID cannot be null");
        }

        if (command.endDate() == null)  {
            throw new IllegalArgumentException("End date cannot be null or ");
        }


        //building
        this.description =  command.description();
        this.timeTask = command.time();
        this.collaboratorId = command.collaboratorId();
        this.endDate=command.endDate();
    }

//Validating date
    public void validateEndDate() {
        if(this.endDate.before(this.createdAt)) {
            throw new IllegalArgumentException("End date cannot be before the creation date");
        }
    }
    public Task(){

    }


//    UPDATE THE AGGREGATE
    public void  updateDescription(String description){
        this.description = description;
    }
    public Task updateStatus(String status){
        this.status = status;
        return this;
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

    public Date getEndDate() {
        return endDate;
    }

    public void setFarmerId(Long id) {
        this.farmerId = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setStatus(String pending) {
        this.status = pending;
    }
}
