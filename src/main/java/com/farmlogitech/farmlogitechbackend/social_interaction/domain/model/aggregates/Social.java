package com.farmlogitech.farmlogitechbackend.social_interaction.domain.model.aggregates;

import com.farmlogitech.farmlogitechbackend.social_interaction.domain.model.commands.CreateSocialCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Social extends AbstractAggregateRoot <Social> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Integer rating;

    public Social (CreateSocialCommand command)
    {
        this.rating = command.rating();
    }

    public Social() {

    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setRating(Integer rating)
    {
        this.rating = rating;
    }
}
