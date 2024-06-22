package com.farmlogitech.farmlogitechbackend.profiles.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Employee {
    @Column(nullable = false)

    private String name;
    @Column(nullable = false)
    private String lastname;
    @Column(nullable = false)


    private String phone;
    @Column(nullable = false)

    private String username;
    @Column(nullable = false)

    private String password;
    @Column(nullable = false)

    private String position;
    @Column(nullable = false)

    private long farmId;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    public Employee(String name, String phone, String username, String password, String position, long farmId) {
        this.name = name;
        this.lastname= "lastname";
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.position = position;
        this.farmId = farmId;
    }

    public Employee() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPosition() {
        return position;
    }

    public long getFarmId() {
        return farmId;
    }

    public void setFarmId(long farmId) {
        this.farmId = farmId;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
