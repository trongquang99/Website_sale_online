package com.website.online.sale.service.security;

import com.website.online.sale.dtos.security.Permission;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PermissionService {
    public Object verifyToken(String token) {

        Permission payload = Permission.parserFromJWT(token);
        if (Objects.isNull(payload)) {
            return HttpStatus.FORBIDDEN.value();
        }

        // check expires
        long currentTimeSeconds = System.currentTimeMillis() / 1000;

        if (payload.getExp() < currentTimeSeconds){
            return HttpStatus.FORBIDDEN.value();
        }

        List<String> scopes = payload.getScopes();
        if (scopes == null || scopes.isEmpty()) {
            return HttpStatus.FORBIDDEN.value();
        }

        if (payload.getExp() > currentTimeSeconds){
            return HttpStatus.OK.value();
        }
//        if (scopes.contains("super.admin") || scopes.contains("campaign.update.approve") || scopes.contains("all.view")) {
//            return HttpStatus.OK.value();
//        }

        return HttpStatus.FORBIDDEN.value();
    }
}
