package com.farmlogitech.farmlogitechbackend.iam.infrastructure.hashing.bcrypt;


import com.farmlogitech.farmlogitechbackend.iam.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService extends HashingService, PasswordEncoder {

}
