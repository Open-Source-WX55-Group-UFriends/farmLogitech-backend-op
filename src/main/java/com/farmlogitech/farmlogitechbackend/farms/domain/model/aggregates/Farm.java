package com.farmlogitech.farmlogitechbackend.farms.domain.model.aggregates;

import com.farmlogitech.farmlogitechbackend.farms.domain.model.commands.CreateFarmCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Farm extends AbstractAggregateRoot<Farm> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

}



}
