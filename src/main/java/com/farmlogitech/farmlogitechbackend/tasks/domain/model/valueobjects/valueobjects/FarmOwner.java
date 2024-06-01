package com.farmlogitech.farmlogitechbackend.tasks.domain.model.valueobjects.valueobjects;
public record FarmOwner(Long idFarmOwner) {
    public FarmOwner(){this(null);}
    public FarmOwner{
        if(idFarmOwner == null){
            throw new IllegalArgumentException("id of farm owner cannot be null");
        }
    }
}
