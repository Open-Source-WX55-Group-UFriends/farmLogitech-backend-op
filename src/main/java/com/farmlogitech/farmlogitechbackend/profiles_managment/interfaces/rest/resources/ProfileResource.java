package com.farmlogitech.farmlogitechbackend.profiles_managment.interfaces.rest.resources;

public record ProfileResource
        (long id,
         String fullName, String direction,
         String phone, String gender, String birthDate,
         String documentNumber, String documentType, String role) {
}