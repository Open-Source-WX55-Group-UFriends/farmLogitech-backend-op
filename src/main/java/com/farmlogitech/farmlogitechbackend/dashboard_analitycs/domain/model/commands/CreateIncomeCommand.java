package com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.commands;

public record CreateIncomeCommand(String category, String description, double amount, String date, String period) {
}
