package com.farmlogitech.farmlogitechbackend.profiles_managment.interfaces.rest.resources;

public record CreateUserResource( String firstName, String lastName, String direction, String phone, String gender, String birthDate, String documentNumber, String documentType, String role, String email, String password,Integer price, String description, Boolean paid) {
    public CreateUserResource {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("email cannot be null or empty");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("password cannot be null or empty");
        }

    }

}
