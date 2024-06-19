package com.farmlogitech.farmlogitechbackend.profiles.domain.services;


import com.farmlogitech.farmlogitechbackend.profiles.domain.model.aggregates.Profile;
import com.farmlogitech.farmlogitechbackend.profiles.domain.model.commands.CreateProfileCommand;
import com.farmlogitech.farmlogitechbackend.profiles.domain.model.commands.UpdateProfileCommand;

import java.util.Optional;

public interface ProfileCommandService {
    Optional<Profile> handle(CreateProfileCommand command);
    Optional<Profile> handle(UpdateProfileCommand command);

}
