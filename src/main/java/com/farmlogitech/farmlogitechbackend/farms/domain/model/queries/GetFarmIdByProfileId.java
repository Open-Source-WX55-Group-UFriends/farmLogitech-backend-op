package com.farmlogitech.farmlogitechbackend.farms.domain.model.queries;

public record GetFarmIdByProfileId(long UserId) {

    public long userId() {
        return UserId;
    }
}
