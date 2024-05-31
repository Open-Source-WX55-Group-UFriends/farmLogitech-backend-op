package com.farmlogitech.farmlogitechbackend.subscription.domain.model.commands;

public record CreateSubscriptionCommand(
        String firstName, String lastName, String direction, String phone, String gender, String birthDate, String documentNumber, String documentType, String role, Integer price, String description, Boolean paid){


}
