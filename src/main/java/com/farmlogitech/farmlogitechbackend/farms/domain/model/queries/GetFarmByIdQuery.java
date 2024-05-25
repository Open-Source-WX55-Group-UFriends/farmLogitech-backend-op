package com.farmlogitech.farmlogitechbackend.farms.domain.model.queries;

public record GetFarmByIdQuery(int id) {
    public GetFarmByIdQuery {
          if(id==0){
        throw new IllegalArgumentException("id must be greater than zero");
          }
    }
}
