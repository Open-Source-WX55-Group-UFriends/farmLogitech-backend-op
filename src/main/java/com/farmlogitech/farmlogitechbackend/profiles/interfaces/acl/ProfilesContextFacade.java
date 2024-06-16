package com.farmlogitech.farmlogitechbackend.profiles.interfaces.acl;


import com.farmlogitech.farmlogitechbackend.profiles.domain.model.commands.CreateProfileCommand;
import com.farmlogitech.farmlogitechbackend.profiles.domain.services.ProfileCommandService;
import com.farmlogitech.farmlogitechbackend.profiles.domain.services.ProfileQueryService;
import org.springframework.stereotype.Service;

@Service
public class ProfilesContextFacade {
    private final ProfileCommandService profileCommandService;
    private final ProfileQueryService profileQueryService;

    public ProfilesContextFacade(ProfileCommandService profileCommandService, ProfileQueryService profileQueryService) {
        this.profileCommandService = profileCommandService;
        this.profileQueryService = profileQueryService;
    }


    public Long createProfile(String firstName, String lastName, String email, String direction, String documentNumber, String documentType) {
        var createProfileCommand = new CreateProfileCommand(firstName, lastName, email, direction, documentNumber, documentType);
        var profile = profileCommandService.handle(createProfileCommand);
        if (profile.isEmpty()) return 0L;
        return profile.get().getId();
    }



}