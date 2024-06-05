package com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.resource;

public record CreateExpenseResource(String category, String description, double amount, String date, String period) {
}
