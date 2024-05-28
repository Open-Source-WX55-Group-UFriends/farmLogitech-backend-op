package com.farmlogitech.farmlogitechbackend.subscription.domain.model.aggregates;

import com.farmlogitech.farmlogitechbackend.subscription.domain.model.commands.CreateSubscriptionCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Subscription extends AbstractAggregateRoot <Subscription> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private Integer price;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Boolean paid;


    public Subscription(CreateSubscriptionCommand command) {
        this.price = command.price();
        this.description = command.description();
        this.paid = command.paid();
    }

    public Subscription() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }


}
