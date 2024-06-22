package com.farmlogitech.farmlogitechbackend.profiles.domain.model.aggregates;

import com.farmlogitech.farmlogitechbackend.profiles.domain.model.commands.CreateProfileCommand;
import com.farmlogitech.farmlogitechbackend.profiles.domain.model.commands.UpdateProfileCommand;
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




    public Profile(String name, String username, String password, long userId) {
        this.name = new PersonName(name, name);
        this.email=username;
        this.documentType=password;
        this.userId=userId;
        this.direction=username;
        this.documentNumber=password;


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

    public Profile( UpdateProfileCommand command)
    {
        this.name = new PersonName(command.firstName(), command.lastName());
        this.email = command.email();
        this.direction = command.direction();
        this.documentNumber = command.documentNumber();
        this.documentType = command.documentType();
    }


    public void setName(PersonName name) {
        this.name = name;
    }
}
