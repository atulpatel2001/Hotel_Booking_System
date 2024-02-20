package com.gateway.configration;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.core.AbstractOAuth2Token;
import org.springframework.security.oauth2.jwt.Jwt;


import java.time.Instant;

public class TokenUtil implements OAuth2TokenValidator<AbstractOAuth2Token> {

@Override
public OAuth2TokenValidatorResult validate(AbstractOAuth2Token token) {
    if (token instanceof Jwt) {
        Jwt jwt = (Jwt) token;
        long currentTimeSeconds = System.currentTimeMillis() / 1000;

        // Check if the token has an expiration claim and if it's expired.
        if (jwt.getExpiresAt() != null && jwt.getExpiresAt().isBefore(Instant.ofEpochSecond(currentTimeSeconds))) {
            return OAuth2TokenValidatorResult.failure();
        }

        // You can add more custom validation logic here if needed.

        return OAuth2TokenValidatorResult.success();
    } else {
        // Handle non-JWT tokens or tokens of different types.
        return OAuth2TokenValidatorResult.failure();
    }
}
        }