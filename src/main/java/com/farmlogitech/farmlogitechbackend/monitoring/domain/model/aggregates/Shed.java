package com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates;


import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.commands.CreateShedCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Shed extends AbstractAggregateRoot<Shed> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String shedName;
    @Column(nullable = false)
    private String typeShed;
    @Column(nullable = false)
    private String specie;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private Long farmId;

    protected Shed() {}

    public Shed(CreateShedCommand command) {
        this.shedName = command.shedName();
        this.typeShed = command.typeShed();
        this.specie = command.specie();
        this.userId = command.userId();
    }

    public Shed(String shedName, String typeShed, String specie, Long userId, long farmId) {
        this.shedName = shedName;
        this.typeShed = typeShed;
        this.specie = specie;
        this.userId = userId;
        this.farmId = farmId;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public void setShedName(String shedName)
    {
        this.shedName = shedName;
    }

    public void setTypeShed(String typeShed)
    {
        this.typeShed = typeShed;
    }

    public void setSpecie(String specie)
    {
        this.specie = specie;
    }

    public long getFarmId() {
        return farmId;
    }

    public void setFarmId(long farmId) {
        this.farmId = farmId;
    }


}
