package com.farmlogitech.farmlogitechbackend.farms.interfaces.rest.resources;

public record FarmResource(int id, String farmName,
                           String location,
                           String type,
                           String infrastructure,
                           String services,
                           String status,
                           String certificates, String image) {

}
