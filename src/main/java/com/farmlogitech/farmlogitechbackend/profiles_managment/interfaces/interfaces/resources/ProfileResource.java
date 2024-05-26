package com.farmlogitech.farmlogitechbackend.profiles_managment.interfaces.interfaces.resources;

public record ProfileResource(int id, String firstName, String lastName, String direction, String phone, String gender, String birthDate, String documentNumber, String documentType, String role) {
}
