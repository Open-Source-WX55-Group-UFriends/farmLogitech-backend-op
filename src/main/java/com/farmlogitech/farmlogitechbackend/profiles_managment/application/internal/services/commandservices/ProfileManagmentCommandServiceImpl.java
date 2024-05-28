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

    private UserRepository userRepository;
    private ProfileRepository profileRepository;

    public ProfileManagmentCommandServiceImpl(UserRepository userRepository, ProfileRepository profileRepository) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
    }


    @Override
    public Optional<Profile> handle(CreateProfileCommnad command) {
        if(profileRepository.existsById(command.id()))
            throw  new IllegalArgumentException("Profile already exists");
        var newProfile= new Profile(command);
        var createdProfile= profileRepository.save(newProfile);
        return Optional.of(createdProfile);
    }

    @Override
    public Optional<User> handle(CreateUserCommand command) {
        if(userRepository.existsById(command.id()))
            throw  new IllegalArgumentException("User already exists");
        var newProfile= new User(command);
        var createdUser= userRepository.save(newProfile);
        return Optional.of(createdUser);
    }

}
