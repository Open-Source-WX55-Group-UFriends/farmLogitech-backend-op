package com.farmlogitech.farmlogitechbackend.profiles_managment.interfaces;

import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.aggregates.Profile;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.aggregates.User;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.services.ProfileManagementCommandService;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.services.ProfileManagmentQueryService;
import com.farmlogitech.farmlogitechbackend.profiles_managment.interfaces.interfaces.resources.CreateProfileResource;
import com.farmlogitech.farmlogitechbackend.profiles_managment.interfaces.interfaces.resources.CreateUserResource;
import com.farmlogitech.farmlogitechbackend.profiles_managment.interfaces.interfaces.resources.ProfileResource;
import com.farmlogitech.farmlogitechbackend.profiles_managment.interfaces.interfaces.resources.UserResource;
import com.farmlogitech.farmlogitechbackend.profiles_managment.interfaces.interfaces.transform.CreateProfileCommandFromResourceAssembler;
import com.farmlogitech.farmlogitechbackend.profiles_managment.interfaces.interfaces.transform.CreateUserCommandFromResourceAssembler;
import com.farmlogitech.farmlogitechbackend.profiles_managment.interfaces.interfaces.transform.ProfileResourceFromEntityAssembler;
import com.farmlogitech.farmlogitechbackend.profiles_managment.interfaces.interfaces.transform.UserResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {
    private final ProfileManagementCommandService userCommandService;

    public ProfileController(ProfileManagmentQueryService userQueryService, ProfileManagementCommandService userCommandService) {
        this.userCommandService = userCommandService;
    }
    @PostMapping
    public ResponseEntity<ProfileResource> createUser(@RequestBody CreateProfileResource resource) {
        Optional<Profile> profile = userCommandService.handle(CreateProfileCommandFromResourceAssembler.toCommandFromResource(resource));
        return profile.map(resp ->
                        new ResponseEntity<>(ProfileResourceFromEntityAssembler
                                .toResourceFromEntity(resp), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
