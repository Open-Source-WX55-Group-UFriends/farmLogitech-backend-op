package com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.resources;

public record CropResource(long id,
                           String shed,
                           String typeCrop,
                           String seedtime) {
}

