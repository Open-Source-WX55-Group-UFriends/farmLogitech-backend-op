package com.farmlogitech.farmlogitechbackend.farms.domain.model.aggregates;

import com.farmlogitech.farmlogitechbackend.farms.domain.model.commands.CreateFarmCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;


//no se si ta bien esto



@Entity
@EntityListeners(AuditingEntityListener.class)
public class Farm extends AbstractAggregateRoot<Farm> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private String farm_name;

    @Column(nullable = false)
    @Getter
    private String location;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDate created_at;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDate updated_at;

    protected Farm() {
    }

    public Farm(CreateFarmCommand command) {
        this.farm_name = command.farmName();
        this.location = command.location();
    }

}
