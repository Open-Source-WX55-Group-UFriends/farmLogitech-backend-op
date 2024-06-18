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
    private Long id;



    @Column(nullable = false)
    private Integer rating;
    @Column(nullable = false)
    private Integer profileId;
    @Column(nullable = false)
    private Integer farmId;


    public Social (CreateSocialCommand command)
    {

        this.rating = command.rating();
        this.farmId= command.farmId();

    }

    public Social() {

    }







    public void setRating(Integer rating)
    {
        this.rating = rating;
    }

    public Integer getRating() {
        return rating;
    }

    public Integer getProfileId() {
        return profileId;
    }

    public Integer getFarmId() {
        return farmId;
    }

    public void setProfileId(Long id) {
        this.profileId=id.intValue();
    }
}
