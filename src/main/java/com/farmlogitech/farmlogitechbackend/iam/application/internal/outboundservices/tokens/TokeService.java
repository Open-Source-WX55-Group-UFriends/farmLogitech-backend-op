package com.farmlogitech.farmlogitechbackend.iam.application.internal.outboundservices.tokens;

public interface TokeService {
    String generateToken(String username);
    String getUsernameFromToken(String token);
    boolean validateToken(String token);
}
