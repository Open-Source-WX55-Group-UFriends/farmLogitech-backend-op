package com.farmlogitech.farmlogitechbackend.farms.domain.model.queries;

public record GetFarmByIdQuery(Integer id) {
    public GetFarmByIdQuery {
          if(id==null || id==0){
        throw new IllegalArgumentException("id must be greater than zero");
          }
    }
}
