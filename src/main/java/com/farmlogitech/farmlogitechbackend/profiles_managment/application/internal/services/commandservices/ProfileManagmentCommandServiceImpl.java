package com.farmlogitech.farmlogitechbackend.profiles_managment.application.internal.services.commandservices;

import com.farmlogitech.farmlogitechbackend.farms.domain.model.aggregates.Farm;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.aggregates.Profile;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.aggregates.User;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.commands.CreateProfileCommnad;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.commands.CreateUserCommand;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.services.ProfileManagementCommandService;
import com.farmlogitech.farmlogitechbackend.profiles_managment.infrastructure.persistence.jpa.ProfileRepository;
import com.farmlogitech.farmlogitechbackend.profiles_managment.infrastructure.persistence.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service

public class ProfileManagmentCommandServiceImpl implements ProfileManagementCommandService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    public ProfileManagmentCommandServiceImpl(UserRepository userRepository, ProfileRepository profileRepository) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
    }


    @Override
    public Optional<Profile> handle(CreateProfileCommnad command) {
    var profile= new Profile(command);
    profileRepository.save(profile);
    return Optional.of(profile);
    }

    @Override
    public Optional<User> handle(CreateUserCommand command) {
        //valid with email
        //boolean
        return Optional.empty();
    }
}
