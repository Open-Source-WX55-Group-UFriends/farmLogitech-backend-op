package com.farmlogitech.farmlogitechbackend.profiles.interfaces.rest;


import com.farmlogitech.farmlogitechbackend.iam.infrastructure.authorization.sfs.model.UserDetailsImpl;
import com.farmlogitech.farmlogitechbackend.profiles.domain.model.aggregates.Profile;
import com.farmlogitech.farmlogitechbackend.profiles.domain.model.commands.CreateProfileCommand;
import com.farmlogitech.farmlogitechbackend.profiles.domain.model.commands.UpdateProfileCommand;
import com.farmlogitech.farmlogitechbackend.profiles.domain.model.queries.GetProfileByIdQuery;
import com.farmlogitech.farmlogitechbackend.profiles.domain.services.ProfileCommandService;
import com.farmlogitech.farmlogitechbackend.profiles.domain.services.ProfileQueryService;
import com.farmlogitech.farmlogitechbackend.profiles.interfaces.rest.resources.CreateProfileResource;
import com.farmlogitech.farmlogitechbackend.profiles.interfaces.rest.resources.ProfileResource;
import com.farmlogitech.farmlogitechbackend.profiles.interfaces.rest.resources.UpdateProfileResource;
import com.farmlogitech.farmlogitechbackend.profiles.interfaces.rest.transform.CreateProfileCommandFromResourceAssembler;
import com.farmlogitech.farmlogitechbackend.profiles.interfaces.rest.transform.ProfileResourceFromEntityAssembler;
import com.farmlogitech.farmlogitechbackend.profiles.interfaces.rest.transform.UpdateProfileCommandFromResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping(value = "/api/v1/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Profiles", description = "Profile Management Endpoints")
public class ProfilesController {
    private final ProfileCommandService profileCommandService;
    private final ProfileQueryService profileQueryService;

    public ProfilesController(ProfileCommandService profileCommandService, ProfileQueryService profileQueryService) {
        this.profileCommandService = profileCommandService;
        this.profileQueryService = profileQueryService;
    }

    @PostMapping
    public ResponseEntity<ProfileResource> createProfile(@RequestBody CreateProfileResource resource) {
        CreateProfileCommand createProfileCommand = CreateProfileCommandFromResourceAssembler.toCommandFromResource(resource);
        var profile = profileCommandService.handle(createProfileCommand);
        if (profile.isEmpty()) return ResponseEntity.badRequest().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return new ResponseEntity<>(profileResource, HttpStatus.CREATED);
    }

    @GetMapping("/me")
    public ResponseEntity<ProfileResource> getMyProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        var getProfileByIdQuery = new GetProfileByIdQuery(userDetails.getId());
        var profile = profileQueryService.handle(getProfileByIdQuery);
        if (profile.isEmpty()) return ResponseEntity.notFound().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(profileResource);
    }
    @PutMapping("/me/edit")
    public ResponseEntity<ProfileResource> updateProfile(@RequestBody UpdateProfileResource resource) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        long userId = userDetails.getId();

        UpdateProfileCommand updateProfileCommand = UpdateProfileCommandFromResource.toCommandFromResource(resource);
        Optional<Profile> updatedProfileOptional = profileCommandService.handle(updateProfileCommand);

        return updatedProfileOptional
                .filter(updatedProfile -> updatedProfile.getUserId() == userId)
                .map(updatedProfile -> ResponseEntity.ok(ProfileResourceFromEntityAssembler.toResourceFromEntity(updatedProfile)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
