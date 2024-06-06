package com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.commands.CreateExpenseCommand;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.commands.CreateIncomeCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Expense extends AbstractAggregateRoot<Expense> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)

    private String category;
    @Column(nullable = false)

    private String description;
    @Column(nullable = false)

    private double amount;
    @Column(nullable = false)

    private String date;
    @Column(nullable = false)

    private String period;
    public Expense(CreateExpenseCommand command) {
        this.category = command.category();
        this.description = command.description();
        this.amount = command.amount();
        this.date = command.date();
        this.period = command.period();


    }

    public Expense() {

    }
}
