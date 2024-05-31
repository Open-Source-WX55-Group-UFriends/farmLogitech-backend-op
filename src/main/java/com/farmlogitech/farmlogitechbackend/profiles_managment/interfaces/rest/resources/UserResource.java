package com.farmlogitech.farmlogitechbackend.profiles_managment.interfaces.rest.resources;

import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.aggregates.Profile;

public record UserResource (int id, String email, String password, Profile profile){
}
