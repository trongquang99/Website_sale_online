package com.website.online.sale.dtos.security;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.Base64;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Permission {
    private String jti;
    private Long exp;
    private String iat;
    private String iss;
    private String sub;
    private List<String> scopes;
    private String username;

    public static Permission parserFromJWT(String token) {
        try {
            String[] arr = token.split("\\.");
            byte[] payload = Base64.getUrlDecoder().decode(arr[1]);
            return new ObjectMapper().readValue(payload, Permission.class);
        } catch (Exception ex) {
            return null;
        }
    }
}
