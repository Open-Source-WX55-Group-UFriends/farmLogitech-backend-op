package com.farmlogitech.farmlogitechbackend.profiles_managment.interfaces.rest.resources;

public record UpdateProfileResource(String firstName, String lastName, String direction, String phone, String gender, String birthDate, String documentNumber, String documentType, String role) {
}
