package com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates;

import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.commands.CreateMessageCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Message extends AbstractAggregateRoot<Message> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Long collaboratorId;

    @Column(nullable = false)
    private Long farmerId;

    protected Message() {
    }

    public Message(CreateMessageCommand command) {
        this.description = command.description();
        this.collaboratorId = command.collaboratorId();
        this.farmerId = command.farmerId();
    }

    public Message(String description, Long collaboratorId, Long farmerId) {
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

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Long getCollaboratorId() {
        return collaboratorId;
    }

    public Long getFarmerId() {
        return farmerId;
    }
}