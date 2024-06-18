package com.farmlogitech.farmlogitechbackend.farms.domain.model.commands;

public record UpdateFarmCommand (String farmName,
                                 String location,
                                 String type,
                                 String infrastructure,
                                 String services,
                                 String status,
                                 String certificates, String image,
                                 double price, String Surface, String product, String highlights){
    public UpdateFarmCommand {
        if (farmName == null || farmName.isBlank()) {
            throw new IllegalArgumentException("Farm name cannot be null or empty");
        }
        if (location == null || location.isBlank()) {
            throw new IllegalArgumentException("Location cannot be null or empty");
        }
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("type cannot be null or empty");
        }

        if (infrastructure == null || infrastructure.isBlank()) {
            throw new IllegalArgumentException("Location cannot be null or empty");
        }
        if (services == null || services.isBlank()) {
            throw new IllegalArgumentException("services cannot be null or empty");
        }
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Location cannot be null or empty");
        }

        if (certificates == null || certificates.isBlank()) {
            throw new IllegalArgumentException("certificates cannot be null or empty");
        }
        if (image == null || image.isBlank()) {
            throw new IllegalArgumentException("images cannot be null or empty");
        }
        if (price == 0) {
            throw new IllegalArgumentException("price cannot be null or empty");
        }
        if (Surface == null || Surface.isBlank()) {
            throw new IllegalArgumentException("Surface cannot be null or empty");
        }
        if (product == null || product.isBlank()) {
            throw new IllegalArgumentException("product cannot be null or empty");
        }
        if (highlights == null || highlights.isBlank()) {
            throw new IllegalArgumentException("highlights cannot be null or empty");
        }


    }
}