package com.website.online.sale.service.auth;

import com.website.online.sale.dtos.request.UserAuthRequest;
import com.website.online.sale.dtos.security.AuthenticationResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    AuthenticationResponse getToken(UserAuthRequest request);

}
