package com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.commands;

public record CreateProfileCommnad(
        String firstName, String lastName, String direction, String phone, String gender, String birthDate, String documentNumber, String documentType, String role) {

    public CreateProfileCommnad {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("firstName cannot be null or empty");
        }
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("lastName cannot be null or empty");
        }
        if (direction == null || direction.isBlank()) {
            throw new IllegalArgumentException("direction cannot be null or empty");
        }
        if (phone == null || phone.isBlank()) {
            throw new IllegalArgumentException("phone cannot be null or empty");
        }
        if (gender == null || gender.isBlank()) {
            throw new IllegalArgumentException("gender cannot be null or empty");
        }
        if (birthDate == null || birthDate.isBlank()) {
            throw new IllegalArgumentException("birthDate cannot be null or empty");
        }
        if (documentNumber == null || documentNumber.isBlank()) {
            throw new IllegalArgumentException("documentNumber cannot be null or empty");
        }
        if (documentType == null || documentType.isBlank()) {
            throw new IllegalArgumentException("documentType cannot be null or empty");
        }
        if (role == null || role.isBlank()) {
            throw new IllegalArgumentException("documentType cannot be null or empty");
        }

    }
}
