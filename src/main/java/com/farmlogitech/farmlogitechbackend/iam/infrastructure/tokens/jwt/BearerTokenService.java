package com.farmlogitech.farmlogitechbackend.iam.infrastructure.tokens.jwt;


import com.farmlogitech.farmlogitechbackend.iam.application.internal.outboundservices.tokens.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

public interface BearerTokenService extends TokenService {

    String getBearerTokenFrom(HttpServletRequest request);
    String generateToken(Authentication authentication);

}
