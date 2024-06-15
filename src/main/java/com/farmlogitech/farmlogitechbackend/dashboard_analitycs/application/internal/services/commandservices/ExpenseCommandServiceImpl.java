package com.farmlogitech.farmlogitechbackend.dashboard_analitycs.application.internal.services.commandservices;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates.Expense;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.commands.CreateExpenseCommand;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.valueobjects.EIExpenseCategory;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.services.ExpenseCommandService;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.infrastructure.persistence.jpa.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class ExpenseCommandServiceImpl implements ExpenseCommandService {
    private final ExpenseRepository expenseRepository;

    public ExpenseCommandServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public Optional<Expense> handle(CreateExpenseCommand command) {
        if (command.amount() <= 0) {
            throw new IllegalArgumentException("El monto del gasto debe ser mayor a cero");
        }
        if (command.category() != EIExpenseCategory.SERVICES && command.category() != EIExpenseCategory.SUPPLIES && command.category() != EIExpenseCategory.OTHER && command.category() != EIExpenseCategory.LABOR && command.category() != EIExpenseCategory.MAINTENANCE) {
            throw new IllegalArgumentException("Categoría inválida. La categoría debe ser RENTA, SALARIO u OTRO.");
        }
        var expense = new Expense(command);
        var createdExpense = expenseRepository.save(expense);
        return Optional.of(createdExpense);
    }
}
