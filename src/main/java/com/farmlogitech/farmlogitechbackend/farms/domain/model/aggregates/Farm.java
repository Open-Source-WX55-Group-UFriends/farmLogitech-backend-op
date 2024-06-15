package com.farmlogitech.farmlogitechbackend.farms.domain.model.aggregates;

import com.farmlogitech.farmlogitechbackend.farms.domain.model.commands.CreateFarmCommand;
import com.farmlogitech.farmlogitechbackend.farms.domain.model.commands.UpdateFarmCommand;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;



@Setter
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Farm extends AbstractAggregateRoot<Farm> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String farmName;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private String infrastructure;
    @Column(nullable = false)
    private String services;
    @Column(nullable = false)
    private String status;
    @Column(nullable = false)
    private String certificates;
    @Column(nullable = false)
    private String image;
    @Column(nullable = false)
    private long profileId;
    @Column(nullable = false)
   private double price;
    @Column(nullable = false)
    private String Surface;
    @Column(nullable = false)
    private String product;
    @Column(nullable = false)
    private String highlights;

    protected Farm() {

    }
public Farm(CreateFarmCommand command) {
    this.farmName= command.farmName();
    this.location= command.location();
    this.type=command.type();
    this.infrastructure=command.infrastructure();
    this.services=command.services();
    this.status= command.status();
    this.certificates=command.certificates();
    this.image=command.image();
    this.profileId=command.profileId();
    this.price=command.price();
    this.Surface=command.Surface();
    this.product=command.product();
    this.highlights=command.highlights();


}
    public Farm updateInformation(UpdateFarmCommand command) {
        this.farmName= command.farmName();
        this.location= command.location();
        this.type=command.type();
        this.infrastructure=command.infrastructure();
        this.services=command.services();
        this.status= command.status();
        this.certificates=command.certificates();
        this.image=command.image();
        this.profileId=command.profileId();
        this.price=command.price();
        this.Surface=command.Surface();
        this.product=command.product();
        this.highlights=command.highlights();


        return null;
    }


}
