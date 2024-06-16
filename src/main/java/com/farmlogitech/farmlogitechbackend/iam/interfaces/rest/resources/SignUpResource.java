package com.farmlogitech.farmlogitechbackend.iam.interfaces.rest.resources;

import java.util.List;

public record SignUpResource(String username, String password, List<String> roles) {
}
