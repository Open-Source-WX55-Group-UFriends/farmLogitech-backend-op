package com.farmlogitech.farmlogitechbackend.profiles.application.internal.comandservices;

import com.farmlogitech.farmlogitechbackend.iam.infrastructure.authorization.sfs.model.UserDetailsImpl;
import com.farmlogitech.farmlogitechbackend.profiles.domain.model.aggregates.Profile;
import com.farmlogitech.farmlogitechbackend.profiles.domain.model.commands.CreateProfileCommand;
import com.farmlogitech.farmlogitechbackend.profiles.domain.services.ProfileCommandService;
import com.farmlogitech.farmlogitechbackend.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {
    private final ProfileRepository profileRepository;

    public ProfileCommandServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Optional<Profile> handle(CreateProfileCommand command) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // Check if a profile already exists for the user
        List<Profile> existingProfile = profileRepository.findByUserId(userDetails.getId());
        if (!existingProfile.isEmpty()) {
            throw new IllegalArgumentException("A profile already exists for this user");
        }

        List<Profile> profilesByEmail = profileRepository.findByEmail(command.email());
        List<Profile> profilesByDocumentNumber = profileRepository.findByDocumentNumber(command.documentNumber());

        if (!profilesByEmail.isEmpty()) {
            throw new IllegalArgumentException("Email already exists");
        }

        if (!profilesByDocumentNumber.isEmpty()) {
            throw new IllegalArgumentException("Document number already exists");
        }

        var profile = new Profile(command, userDetails.getId());

        profileRepository.save(profile);

        return Optional.of(profile);
    }
}