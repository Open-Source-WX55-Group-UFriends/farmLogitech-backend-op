package com.farmlogitech.farmlogitechbackend.profiles.application.internal.comandservices;


import com.farmlogitech.farmlogitechbackend.profiles.domain.model.aggregates.Profile;
import com.farmlogitech.farmlogitechbackend.profiles.domain.model.commands.CreateProfileCommand;
import com.farmlogitech.farmlogitechbackend.profiles.domain.model.valueobjects.PersonName;
import com.farmlogitech.farmlogitechbackend.profiles.domain.services.ProfileCommandService;
import com.farmlogitech.farmlogitechbackend.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {
    private final ProfileRepository profileRepository;

    public ProfileCommandServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Optional<Profile> handle(CreateProfileCommand command) {
        var profile = new Profile(command);
        profileRepository.save(profile);
        return Optional.of(profile);
    }

}
