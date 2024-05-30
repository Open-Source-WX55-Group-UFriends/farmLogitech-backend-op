package com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.aggregates;

import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.commands.CreateProfileCommnad;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.valueobjects.PersonName;
import com.farmlogitech.farmlogitechbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
public class Profile extends AuditableAbstractAggregateRoot<Profile> {

    @Embedded
    private PersonName name;

    @Column(nullable = false)
    private String direction;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String gender;
    @Column(nullable = false)
    private String birthDate;
    @Column(nullable = false)
    private String documentNumber;
    @Column(nullable = false)
    private String documentType;
    @Column(nullable = false)
    private String role;



    public Profile() {

    }

    public Profile(String firstName, String lastName, String direction, String phone, String gender, String birthDate, String documentNumber, String documentType, String role) {
        this.name = new PersonName(firstName, lastName);
        this.direction = direction;
        this.phone = phone;
        this.gender = gender;
        this.birthDate = birthDate;
        this.documentNumber = documentNumber;
        this.documentType = documentType;
        this.role = role;
    }
    public void updateName(String firstName, String lastName) {
        this.name = new PersonName(firstName, lastName);
    }

    public String getFullName() {
        return name.getFullName();
    }
    public Profile(CreateProfileCommnad command){
        this.name = new PersonName(command.firstName(), command.lastName());
        this.direction = command.direction();
        this.phone = command.phone();
        this.gender = command.gender();
        this.birthDate = command.birthDate();
        this.documentNumber = command.documentNumber();
        this.documentType = command.documentType();
        this.role = command.role();

    }



    public String getDirection() {
        return direction;
    }

    public String getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public String getDocumentType() {
        return documentType;
    }

    public String getRole() {
        return role;
    }
}
