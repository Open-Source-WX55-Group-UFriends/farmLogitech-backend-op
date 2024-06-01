package com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.aggregates;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.valueobjects.ProfileId;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.valueobjects.SubscriptionId;
import jakarta.persistence.*;
import org.springframework.data.domain.AbstractAggregateRoot;

@Entity
public class User extends AbstractAggregateRoot<User>  {

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;

    @Embedded
    private ProfileId profileId;
    @Embedded
    private SubscriptionId subscriptionId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected User() {

    }

    public Long getSubscriptionId() {
        return this.subscriptionId.subscriptionId();
    }
    public User(ProfileId profileId, SubscriptionId subscriptionId, String email, String password) {
        this.profileId = profileId;
        this.subscriptionId= subscriptionId;
        this.password = password;
        this.email=email;

    }






    public ProfileId profileId() {
        return this.profileId;
    }
    public SubscriptionId subscriptionId() {
        return this.subscriptionId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


    public Long getProfileId() {
        return this.profileId.profileId();
    }
}