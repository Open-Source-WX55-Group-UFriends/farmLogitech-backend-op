package com.farmlogitech.farmlogitechbackend.tasks.domain.model.aggregates;

import com.farmlogitech.farmlogitechbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.farmlogitech.farmlogitechbackend.tasks.domain.model.commands.commands.CreateTaskCommand;
import com.farmlogitech.farmlogitechbackend.tasks.domain.model.valueobjects.valueobjects.*;
import jakarta.persistence.*;

import java.sql.Time;
import java.time.LocalDate;

@Entity
public class Task extends AuditableAbstractAggregateRoot<Task> {
    @Embedded
    private Description description;

    @Embedded
    private Status status;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "time", column = @Column(name = "time_hours")),
            @AttributeOverride(name = "endDate", column = @Column(name = "time_end_date"))
    })
    private TimeTask timeTask;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "idCollaborator", column = @Column(name = "id_collaborator"))
    })
    private Collaborator collaborator;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "idFarmOwner", column = @Column(name = "id_farm_owner"))
    })
    private FarmOwner farmOwner;

    public Task(String description, String status, int time, LocalDate endDate, Long idCollaborator, Long idFarmOwner){//Be careful with the int and the local date
        this.description = new Description(description);
        this.status = new Status(status);
        this.timeTask = new TimeTask(time, endDate);
        this.collaborator = new Collaborator(idCollaborator);
        this.farmOwner = new FarmOwner(idFarmOwner);
    }

    public Task(CreateTaskCommand command){
        this.description= new Description(command.description());
        this.status = new Status(command.status());
        this.timeTask = new TimeTask(command.time(), command.endDate());
        this.collaborator = new Collaborator(command.idCollaborator());
        this.farmOwner = new FarmOwner(command.idFarmOwner());
    }

    public Task(){

    }
//    UPDATE THE AGGREGATE
    public void  updateDescription(String description){
        this.description = new Description(description);

    }
    public void updateStatus(String status){
        this.status = new Status(status);
    }
    public void updateTimeTask(int time, LocalDate endDate ){
        this.timeTask = new TimeTask(time, endDate);
    }
    public void updateCollaborator(Long idCollaborator){this.collaborator = new Collaborator(idCollaborator);}

    public String getFullTimeTask(){
        return timeTask.getFullTime();
    }
}
