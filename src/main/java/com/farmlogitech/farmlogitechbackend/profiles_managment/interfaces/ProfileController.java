package com.farmlogitech.farmlogitechbackend.profiles_managment.interfaces;

import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.aggregates.Profile;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.queries.GetAllProfilesQuery;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.queries.GetProfileById;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.services.ProfileManagementCommandService;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.services.ProfileManagmentQueryService;
import com.farmlogitech.farmlogitechbackend.profiles_managment.interfaces.rest.resources.CreateProfileResource;
import com.farmlogitech.farmlogitechbackend.profiles_managment.interfaces.rest.resources.ProfileResource;
import com.farmlogitech.farmlogitechbackend.profiles_managment.interfaces.rest.transform.CreateProfileCommandFromResourceAssembler;
import com.farmlogitech.farmlogitechbackend.profiles_managment.interfaces.rest.transform.ProfileResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/api/v1/profile")
public class ProfileController {
    private final ProfileManagementCommandService profileCommandService;
    private final ProfileManagmentQueryService profileQueryService;

    public ProfileController(ProfileManagmentQueryService userQueryService, ProfileManagementCommandService userCommandService, ProfileManagmentQueryService profileQueryService) {
        this.profileCommandService = userCommandService;
        this.profileQueryService = profileQueryService;
    }
    @PostMapping
    public ResponseEntity<ProfileResource> createUser(@RequestBody CreateProfileResource resource) {
        Optional<Profile> profile = profileCommandService.handle(CreateProfileCommandFromResourceAssembler.toCommandFromResource(resource));
        return profile.map(resp ->
                        new ResponseEntity<>(ProfileResourceFromEntityAssembler
                                .toResourceFromEntity(resp), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileResource> getProfileById(@PathVariable Long profileId) {
     var getProfileByIdQuery= new GetProfileById(profileId);
     var profile= profileQueryService.handle(getProfileByIdQuery);
     if(profile.isEmpty()) return ResponseEntity.notFound().build();
     var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
     return ResponseEntity.ok(profileResource);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProfileResource>>getAllProfile(){
        var getAllProfilesQuery = new GetAllProfilesQuery();
        var profiles = profileQueryService.handle(getAllProfilesQuery);
        var profileResources = profiles.stream()
                .map(ProfileResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(profileResources);
    }







}
