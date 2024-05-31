package com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.commands;

public record CreateUserCommand (String firstName, String lastName, String direction, String phone, String gender, String birthDate, String documentNumber, String documentType, String role, String email, String password,Integer price, String description, Boolean paid ){



}
