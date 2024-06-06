package com.farmlogitech.farmlogitechbackend.iam.domain.model.entities;

import com.farmlogitech.farmlogitechbackend.iam.domain.model.valueobjects.Roles;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
public class Role {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false, unique = true)
    private Roles name;

    public Role(Roles name) {
        this.name=name;
    }

    public Role() {

    }

    public String getStringName(){
        return name.name();
    }
    public static Role getDefaultRole(){
        return new Role(Roles.ROLE_FARMER);
    }
    public static Role toRoleFromName(String name){
        return new Role(Roles.valueOf(name));
    }
    public static List<Role> validateRoleSet(List<Role>roles) {
        if (roles == null || roles.isEmpty()) {
            return List.of(getDefaultRole());
        }
        return roles;

    }


}
