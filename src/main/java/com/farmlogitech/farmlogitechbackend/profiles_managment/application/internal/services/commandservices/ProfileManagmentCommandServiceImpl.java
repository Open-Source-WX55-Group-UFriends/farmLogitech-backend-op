package com.farmlogitech.farmlogitechbackend.profiles_managment.application.internal.services.commandservices;
import com.farmlogitech.farmlogitechbackend.profiles_managment.application.internal.outboundservices.acl.ExternalSubscriptionService;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.valueobjects.ProfileId;
import lombok.val;
import org.springframework.context.annotation.Lazy;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.aggregates.Profile;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.aggregates.User;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.commands.CreateProfileCommnad;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.commands.CreateUserCommand;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.commands.UpdateProfileCommand;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.services.ProfileManagementCommandService;
import com.farmlogitech.farmlogitechbackend.profiles_managment.infrastructure.persistence.jpa.ProfileRepository;
import com.farmlogitech.farmlogitechbackend.profiles_managment.infrastructure.persistence.jpa.UserRepository;
import com.farmlogitech.farmlogitechbackend.profiles_managment.application.internal.outboundservices.acl.ExternalProfileService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service

public class ProfileManagmentCommandServiceImpl implements ProfileManagementCommandService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final ExternalProfileService externalProfileService;
    private final ExternalSubscriptionService externalSubscriptionService ;

    public ProfileManagmentCommandServiceImpl(
            UserRepository userRepository,
            ProfileRepository profileRepository,
            @Lazy ExternalProfileService externalProfileService,
            @Lazy   ExternalSubscriptionService externalSubscriptionService // La anotación @Lazy retrasa la inicialización de esta dependencia hasta que se necesite.
    ) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.externalProfileService = externalProfileService;
        this.externalSubscriptionService = externalSubscriptionService;
    }


    @Override
    public Optional<Profile> handle(CreateProfileCommnad command) {
    var profile= new Profile(command);
    profileRepository.save(profile);
    return Optional.of(profile);
    }

    @Override
    public Optional<Profile> handle(UpdateProfileCommand command) {
        if (profileRepository.existsById(command.id())){
            var profile = profileRepository.findById(command.id()).get();
            profile.updateInformation(command);
            var updatedProfile = profileRepository.save(profile);
            return Optional.of(updatedProfile);
        }
        throw new IllegalArgumentException("Profile doesn't already exists");
    }

    @Override
    public Optional<User> handle(CreateUserCommand command) {
        if(userRepository.findByEmail(command.email()).isPresent()){
            throw new IllegalArgumentException("User already exists");
        }

        var profileId = externalProfileService.createProfile(command.firstName(),command.lastName(),command.direction(),command.phone(),command.gender(),command.birthDate(),command.documentNumber(),command.documentType(),command.role());
        Long id = profileId.get().getProfileIdc();
        var subscriptionId = externalSubscriptionService.createSubscription(command.price(),command.description(),command.paid(), id);
        var createdUser = new User(profileId.get(), subscriptionId.get(), command.email(), command.password());

        var newUser = userRepository.save(createdUser);
        return  Optional.of(newUser);
    }

}
