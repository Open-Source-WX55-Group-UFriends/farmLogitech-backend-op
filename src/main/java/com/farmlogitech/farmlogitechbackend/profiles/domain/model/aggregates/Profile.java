package com.farmlogitech.farmlogitechbackend.profiles.domain.model.aggregates;

import com.farmlogitech.farmlogitechbackend.profiles.domain.model.commands.CreateProfileCommand;
import com.farmlogitech.farmlogitechbackend.profiles.domain.model.valueobjects.PersonName;
import com.farmlogitech.farmlogitechbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Profile extends AuditableAbstractAggregateRoot<Profile> {
    @Embedded
    private PersonName name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String direction;
    @Column(nullable = false)
    private String documentNumber;
    @Column(nullable = false)
    private String documentType;
    private Long userId;



    public Profile(String firstName, String lastName, String email, String direction, String documentNumber, String documentType) {
        this.name = new PersonName(firstName, lastName);
        this.email = email;
        this.direction=direction;
        this.documentNumber=documentNumber;
        this.documentType=documentType;
    }

    public Profile(CreateProfileCommand command, Long userId) {
        this.name = new PersonName(command.firstName(), command.lastName());
        this.email = command.email();
        this.direction = command.direction();
        this.documentNumber = command.documentNumber();
        this.documentType = command.documentType();
        this.userId = userId;
    }
    public Profile() {

    }

    public void updateName(String firstName, String lastName) {
        this.name = new PersonName(firstName, lastName);
    }


    public String getFullName() { return name.getFullName(); }


    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return name.getFirstName();
    }
    public  String getLastName() {
        return name.getLastName();
    }


}
