package com.farmlogitech.farmlogitechbackend.profiles_managment.interfaces.acl;

import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.commands.CreateProfileCommnad;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.valueobjects.PersonName;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.services.ProfileManagementCommandService;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.services.ProfileManagmentQueryService;
import org.springframework.stereotype.Service;

@Service

public class ProfilesContextFacade {
    private final ProfileManagementCommandService profileCommandService;
    private final ProfileManagmentQueryService profileQueryService;

    public ProfilesContextFacade(ProfileManagementCommandService profileCommandService, ProfileManagmentQueryService profileQueryService) {
        this.profileCommandService = profileCommandService;
        this.profileQueryService = profileQueryService;
    }


    public Long createProfile(String firstName, String lastName, String direction, String phone, String gender, String birthDate, String documentNumber, String documentType, String role) {
       var createProfileCommand = new CreateProfileCommnad( firstName,  lastName,  direction,  phone,  gender,  birthDate,  documentNumber,  documentType, role);
       var profile= profileCommandService.handle(createProfileCommand);
        if (profile.isEmpty()) return 0L;
        return profile.get().getId();
    }



}
