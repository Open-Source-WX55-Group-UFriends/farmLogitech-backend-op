package com.farmlogitech.farmlogitechbackend.profiles_managment.domain.services;

import com.farmlogitech.farmlogitechbackend.farms.domain.model.aggregates.Farm;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.aggregates.Profile;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.aggregates.User;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.commands.CreateProfileCommnad;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.commands.CreateUserCommand;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.commands.UpdateProfileCommand;

import java.util.Optional;

public interface ProfileManagementCommandService {
    Optional<Profile> handle(CreateProfileCommnad command);
    Optional<Profile> handle(UpdateProfileCommand command);
    Optional<User> handle(CreateUserCommand command);

}
