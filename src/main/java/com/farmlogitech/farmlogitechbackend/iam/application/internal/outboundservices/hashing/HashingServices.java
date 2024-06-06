package com.farmlogitech.farmlogitechbackend.iam.application.internal.outboundservices.hashing;

public interface HashingServices {
    String encode(CharSequence rawPassword);
    boolean matches(CharSequence rawPassword,String encodedPassword);
}
