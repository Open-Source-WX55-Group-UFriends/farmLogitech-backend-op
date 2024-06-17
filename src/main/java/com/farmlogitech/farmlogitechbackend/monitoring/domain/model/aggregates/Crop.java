package com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates;


import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.commands.CreateCropCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Crop extends AbstractAggregateRoot<Crop> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String shed;
    @Column(nullable = false)
    private String typeCrop;
    @Column(nullable = false)
    private String seedtime;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private Long farmId;

    protected Crop(){}

    public Crop(CreateCropCommand command) {
        this.shed = command.shed();
        this.typeCrop = command.typeCrop();
        this.seedtime = command.seedtime();
    }

    public Crop(String shed, String typeCrop, String seedtime, Long userId, Long farmId) {
        this.shed = shed;
        this.typeCrop = typeCrop;
        this.seedtime = seedtime;
        this.userId = userId;
        this.farmId = farmId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setShed(String shed) {
        this.shed = shed;
    }

    public void setTypeCrop(String typeCrop) {
        this.typeCrop = typeCrop;
    }

    public void setSeedtime(String seedtime) {
        this.seedtime = seedtime;
    }

    public long getFarmId() {
        return farmId;
    }

    public void setFarmId(long farmId) {
        this.farmId = farmId;
    }

}
