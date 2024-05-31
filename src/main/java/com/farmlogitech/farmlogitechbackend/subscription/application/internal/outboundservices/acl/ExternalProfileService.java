package com.farmlogitech.farmlogitechbackend.subscription.application.internal.outboundservices.acl;

import com.farmlogitech.farmlogitechbackend.profiles_managment.interfaces.acl.ProfilesContextFacade;
import com.farmlogitech.farmlogitechbackend.subscription.domain.model.valueobjects.ProfileId;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class ExternalProfileService {
    private final ProfilesContextFacade profilesContextFacade;

    public ExternalProfileService(ProfilesContextFacade profilesContextFacade) {
        this.profilesContextFacade = profilesContextFacade;
    }
    public Optional<ProfileId> createProfile(String firstName, String lastName, String direction, String phone, String gender, String birthDate, String documentNumber, String documentType, String role) {
        var profileId = profilesContextFacade.createProfile(firstName,  lastName,  direction,  phone,  gender,  birthDate,  documentNumber,  documentType, role);
        if (profileId == 0L) return Optional.empty();
        return Optional.of(new ProfileId(profileId));
    }
}
