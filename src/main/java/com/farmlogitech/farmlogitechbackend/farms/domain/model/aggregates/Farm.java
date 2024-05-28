package com.farmlogitech.farmlogitechbackend.farms.domain.model.aggregates;

import com.farmlogitech.farmlogitechbackend.farms.domain.model.commands.CreateFarmCommand;
import com.farmlogitech.farmlogitechbackend.farms.domain.model.commands.UpdateFarmCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;



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


}
    public Farm(UpdateFarmCommand command) {
        this.farmName= command.farmName();
        this.location= command.location();
        this.type=command.type();
        this.infrastructure=command.infrastructure();
        this.services=command.services();
        this.status= command.status();
        this.certificates=command.certificates();
        this.image=command.image();


    }


    public void setId(int id) {
        this.id = id;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setInfrastructure(String infrastructure) {
        this.infrastructure = infrastructure;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCertificates(String certificates) {
        this.certificates = certificates;
    }
    public void setImage(String image) {
        this.image = image;
    }
}
