package com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class message extends AbstractAggregateRoot<message> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Long collaboratorId;

    @Column(nullable = false)
    private Long farmerId;

    protected message() {
    }

    public message(String description, Long collaboratorId, Long farmerId) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        if (collaboratorId == null || collaboratorId <= 0) {
            throw new IllegalArgumentException("Collaborator ID must be a positive number");
        }
        if (farmerId == null || farmerId <= 0) {
            throw new IllegalArgumentException("Farmer ID must be a positive number");
        }

        this.description = description;
        this.collaboratorId = collaboratorId;
        this.farmerId = farmerId;
    }
}