package com.farmlogitech.farmlogitechbackend.social_interaction.application.internal.services.commandservices;

import com.farmlogitech.farmlogitechbackend.iam.infrastructure.authorization.sfs.model.UserDetailsImpl;
import com.farmlogitech.farmlogitechbackend.social_interaction.domain.model.aggregates.Social;
import com.farmlogitech.farmlogitechbackend.social_interaction.domain.model.commands.CreateSocialCommand;
import com.farmlogitech.farmlogitechbackend.social_interaction.domain.services.SocialCommandService;
import com.farmlogitech.farmlogitechbackend.social_interaction.infrastructure.persistence.jpa.SocialRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SocialCommandServiceImpl implements SocialCommandService {

    private final SocialRepository socialRepository;

    public SocialCommandServiceImpl(SocialRepository socialRepository)
    {
        this.socialRepository = socialRepository;
    }

    @Override
    public Optional<Social> handle(CreateSocialCommand command){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // Check if the authenticated user has the role of ROLE_OWNER
        boolean isOwner = userDetails.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_OWNER"));

        if (!isOwner) {
            throw new IllegalStateException("Only owners can create a social");
        }

        var newSocial = new Social(command);
        newSocial.setProfileId(userDetails.getId());
        var createdSocial = socialRepository.save(newSocial);

        return Optional.of(createdSocial);
    }
}
