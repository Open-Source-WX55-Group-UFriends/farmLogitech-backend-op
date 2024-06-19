package com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.transform;

import com.farmlogitech.farmlogitechbackend.iam.infrastructure.authorization.sfs.model.UserDetailsImpl;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.commands.CreateShedCommand;
import com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.resources.CreateShedResource;
import org.springframework.security.core.context.SecurityContextHolder;

public class CreateShedCommandFromResourceAssembler {
    public static CreateShedCommand toCommandFromResource(CreateShedResource resource) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userDetails.getUserDetailsId();
        return new CreateShedCommand(resource.shedName(),
                resource.typeShed(),
                resource.specie(),
                userId);
    }
}

