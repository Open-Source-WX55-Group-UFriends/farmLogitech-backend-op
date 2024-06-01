package com.farmlogitech.farmlogitechbackend.social_interaction.interfaces;

import com.farmlogitech.farmlogitechbackend.social_interaction.domain.model.aggregates.Social;
import com.farmlogitech.farmlogitechbackend.social_interaction.domain.model.queries.GetAllSocialQuery;
import com.farmlogitech.farmlogitechbackend.social_interaction.domain.model.queries.GetAllSocialsByFarmIdQuery;
import com.farmlogitech.farmlogitechbackend.social_interaction.domain.services.SocialCommandService;
import com.farmlogitech.farmlogitechbackend.social_interaction.domain.services.SocialQueryService;
import com.farmlogitech.farmlogitechbackend.social_interaction.interfaces.interfaces.resources.CreateSocialResource;
import com.farmlogitech.farmlogitechbackend.social_interaction.interfaces.interfaces.resources.SocialResource;
import com.farmlogitech.farmlogitechbackend.social_interaction.interfaces.interfaces.transform.CreateSocialCommandFromResourceAssembler;
import com.farmlogitech.farmlogitechbackend.social_interaction.interfaces.interfaces.transform.SocialResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;


@RestController
@RequestMapping("/api/v1/socials")
public class SocialController {
    private final SocialQueryService socialQueryService;
    private final SocialCommandService socialCommandService;

    public SocialController(SocialQueryService socialQueryService,
                            SocialCommandService socialCommandService)
    {
        this.socialQueryService = socialQueryService;
        this.socialCommandService = socialCommandService;
    }

    @PostMapping
    public ResponseEntity<SocialResource> createSocial(@RequestBody CreateSocialResource resource)
    {
        Optional<Social> social = socialCommandService.handle(CreateSocialCommandFromResourceAssembler.toCommandFromResource(resource));
        return social.map(resp ->
                new ResponseEntity<>(SocialResourceFromEntityAssembler
                        .toResourceFromEntity(resp), CREATED))
                .orElseGet(()-> ResponseEntity.badRequest().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<SocialResource>> getAllSocial() {
        var socials = socialQueryService.handle(new GetAllSocialQuery());
        if (socials.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        var socialResource = socials.stream().map(SocialResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(socialResource);
    }

    @GetMapping("/all/farm/{id}")
    public ResponseEntity<Long>getCountAllSocialByFarmId(@PathVariable long id)
    {
        List<Social> socialQuery = socialQueryService.handle(new GetAllSocialsByFarmIdQuery(id));
        long count = socialQuery.size();
        return ResponseEntity.ok(count);
    }
}
