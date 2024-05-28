package com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.aggregates;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.commands.CreateUserCommand;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User extends AbstractAggregateRoot<User>  {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

        private int id;
    @Setter
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private Profile profile;
    protected User() {

    }




  public User(CreateUserCommand command) {
        this.email = command.email();
        this.password = command.password();
        this.profile=command.profile();
    }
    public void setId(int id) {
        this.id = id;
    }


}