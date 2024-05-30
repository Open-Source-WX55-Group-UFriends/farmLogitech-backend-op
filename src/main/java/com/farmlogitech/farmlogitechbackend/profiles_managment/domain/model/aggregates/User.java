package com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.aggregates;
import com.farmlogitech.farmlogitechbackend.subscription.domain.model.valueobjects.ProfileId;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected User() {

    }
    public Long getProfileId() {
        return this.profileId.profileId();
    }
    public User(ProfileId profileId, String email, String password) {
        this.profileId = profileId;
        this.password = password;
        this.email=email;

    }






    public ProfileId profileId() {
        return this.profileId;
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


}