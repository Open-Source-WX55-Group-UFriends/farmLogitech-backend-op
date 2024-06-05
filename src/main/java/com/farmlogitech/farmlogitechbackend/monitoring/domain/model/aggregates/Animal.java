package com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates;


import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.commands.CreateAnimalCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Animal extends AbstractAggregateRoot<Animal> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String shed;
    @Column(nullable = false)
    private Integer age;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private String healthCondition;

    protected Animal(){}

    public Animal(CreateAnimalCommand command) {
        this.shed = command.shed();
        this.age = command.age();
        this.location = command.location();
        this.healthCondition = command.healthCondition();
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setShed(String shed) {
        this.shed = shed;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setHealthCondition(String healthCondition) {
        this.healthCondition = healthCondition;
    }
}
