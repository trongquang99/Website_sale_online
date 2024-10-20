package com.website.online.sale.service.auth;

import com.website.online.sale.dtos.request.UserAuthRequest;
import com.website.online.sale.dtos.security.AuthenticationResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {
    @Value("${auth.secret.key}")
    private String gamJwtClientSecret;

    @Override
    public AuthenticationResponse getToken(UserAuthRequest authRequest) {
        //TODO: gen accessToken
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 86400000);
        Map<String, Object> payloadAccessToken = new HashMap<>();
        payloadAccessToken.put("iss", "car_service_auth_token");
        payloadAccessToken.put("username",authRequest.getUsername());
        payloadAccessToken.put("scopes", authRequest.getScopes());
        payloadAccessToken.put("iat", now);
        payloadAccessToken.put("exp", expiration);

        String accessToken = Jwts.builder()
                .setClaims(payloadAccessToken)
                .setId(generateTokenId())
                .signWith(SignatureAlgorithm.HS256, gamJwtClientSecret)
                .compact();

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .tokenType("Bearer")
                .expiresIn(expiration.getTime())
                .build();
    }
    private static String generateTokenId() {
        return UUID.randomUUID().toString();
    }

}
