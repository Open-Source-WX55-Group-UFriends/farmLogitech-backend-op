package com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.commands.CreateExpenseCommand;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.valueobjects.EIExpenseCategory;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.valueobjects.EIncomeCategory;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Expense extends AbstractAggregateRoot<Expense> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EIExpenseCategory category;
    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double amount;

    @NotNull(message = "The date is mandatory")
    private LocalDate date;

    @Column(nullable = false)
    private String period;

    @Column(nullable = false)
    private long farmId;

    public Expense(CreateExpenseCommand command) {
        this.category = command.category();
        this.description = command.description();
        this.amount = command.amount();
        this.date = command.date();
        this.period = command.period();
    }


    public Expense() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EIExpenseCategory getCategory() {
        return category;
    }

    public void setCategory(EIExpenseCategory category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public @NotNull(message = "The date is mandatory") LocalDate getDate() {
        return date;
    }

    public void setDate(@NotNull(message = "The date is mandatory") LocalDate date) {
        this.date = date;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public long getFarmId() {
        return farmId;
    }

    public void setFarmId(long farmId) {
        this.farmId = farmId;
    }
}