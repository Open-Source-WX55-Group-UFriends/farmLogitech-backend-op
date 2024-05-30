package com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.commands;

public record UpdateProfileCommand (Long id, String firstName, String lastName, String direction, String phone, String gender, String birthDate, String documentNumber, String documentType, String role){
}
