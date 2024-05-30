package com.farmlogitech.farmlogitechbackend.subscription.domain.model.aggregates;

import com.farmlogitech.farmlogitechbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.farmlogitech.farmlogitechbackend.subscription.domain.model.commands.CreateSubscriptionCommand;
import com.farmlogitech.farmlogitechbackend.subscription.domain.model.commands.UpdateSubscriptionCommand;
import com.farmlogitech.farmlogitechbackend.subscription.domain.model.valueobjects.ProfileId;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
public class Subscription extends AuditableAbstractAggregateRoot<Subscription> {

    @Embedded
    private ProfileId profileId;
    @Column(nullable = false)
    private Integer price;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Boolean paid;




    public Subscription() {

    }

    public Subscription(ProfileId profileId, Integer price, String description, Boolean paid) {
        this.profileId = profileId;
        this.price = price;
        this.description = description;
        this.paid = paid;
    }


    public Subscription updateInformation(UpdateSubscriptionCommand command)
    {
        this.profileId = command.profileId();
        this.price = command.price();
        this.description = command.description();
        this.paid = command.paid();
        return this;
    }


    public Long getProfileId() {
        return this.profileId.profileId();
    }

    public Integer getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getPaid() {
        return paid;
    }
}
