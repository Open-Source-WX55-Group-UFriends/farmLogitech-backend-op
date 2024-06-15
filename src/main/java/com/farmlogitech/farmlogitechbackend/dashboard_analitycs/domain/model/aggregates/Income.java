package com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.commands.CreateIncomeCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Income extends AbstractAggregateRoot<Income> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IncomeCategory category;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double amount;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate date;

    @Column(nullable = false)
    private String period;

    public Income(CreateIncomeCommand command) {
        this.category = IncomeCategory.valueOf(command.category());
        this.description = command.description();
        this.amount = command.amount();
        this.date = command.date();
        this.period = command.period();
    }

    public Income() {
    }

    public enum IncomeCategory {
        VENTAS, INVERSIONES, OTROS
    }
}