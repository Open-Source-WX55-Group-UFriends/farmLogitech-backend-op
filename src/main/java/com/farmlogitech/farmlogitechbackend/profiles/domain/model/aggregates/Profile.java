package com.farmlogitech.farmlogitechbackend.profiles.domain.model.aggregates;

import com.farmlogitech.farmlogitechbackend.profiles.domain.model.commands.CreateProfileCommand;
import com.farmlogitech.farmlogitechbackend.profiles.domain.model.valueobjects.PersonName;
import com.farmlogitech.farmlogitechbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;

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



    public Profile(String firstName, String lastName, String email, String direction, String documentNumber, String documentType) {
        this.name = new PersonName(firstName, lastName);
        this.email = email;
        this.direction=direction;
        this.documentNumber=documentNumber;
        this.documentType=documentType;

    }

    public Profile(CreateProfileCommand command) {
        this.name = new PersonName(command.firstName(), command.lastName());
        this.email = command.email();
        this.direction=command.direction();
        this.documentNumber=command.documentNumber();
        this.documentType=command.documentType();

    }
    public Profile() {

    }

    public void updateName(String firstName, String lastName) {
        this.name = new PersonName(firstName, lastName);
    }


    public String getFullName() { return name.getFullName(); }


    public PersonName getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDirection() {
        return direction;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public String getDocumentType() {
        return documentType;
    }
}
