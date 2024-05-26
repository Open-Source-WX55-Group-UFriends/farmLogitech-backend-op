package com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.aggregates;

import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.commands.CreateProfileCommnad;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Profile extends AbstractAggregateRoot<Profile> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
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

    public Profile(CreateProfileCommnad command) {
        this.firstName = command.firstName();
        this.lastName = command.lastName();
        this.direction = command.direction();
        this.phone = command.phone();
        this.gender = command.gender();
        this.birthDate = command.birthDate();
        this.documentNumber = command.documentNumber();
        this.documentType = command.documentType();
        this.role= command.role();
    }

    public Profile() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
