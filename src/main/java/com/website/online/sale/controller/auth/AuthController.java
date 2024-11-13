package com.website.online.sale.controller.auth;

import com.website.online.sale.base.BaseResponse;
import com.website.online.sale.base.ResponseBuilder;
import com.website.online.sale.dtos.request.UserAuthRequest;
import com.website.online.sale.dtos.security.AuthenticationResponse;
import com.website.online.sale.service.auth.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "API xác thực")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(description = "Gen token of user")
    @GetMapping("/api/v1/auth/token")
    public ResponseEntity<BaseResponse<AuthenticationResponse>> identifyToken(
            @ParameterObject UserAuthRequest authRequest
    ) {
        try {
            AuthenticationResponse responseToken = authService.getToken(authRequest);
            return ResponseEntity.ok(ResponseBuilder.success(responseToken));
        } catch (Exception e) {
            return ResponseEntity.ok(ResponseBuilder.error(e.getMessage()));
        }
    }
}

