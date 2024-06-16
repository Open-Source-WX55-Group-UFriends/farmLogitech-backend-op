package com.farmlogitech.farmlogitechbackend.iam.application.internal.outboundservices.acl;

import com.farmlogitech.farmlogitechbackend.farms.domain.model.queries.PutFarmById;
import com.farmlogitech.farmlogitechbackend.farms.interfaces.acl.FarmContextFacade;
import org.springframework.stereotype.Service;

@Service
public class ExternalFarmService {
    private final FarmContextFacade farmContextFacade;

    public ExternalFarmService(FarmContextFacade farmContextFacade) {
        this.farmContextFacade = farmContextFacade;
    }

    public long fetchFarmIdByUserId(long userId) {
        return farmContextFacade.findByProfileId(userId);
    }
}
