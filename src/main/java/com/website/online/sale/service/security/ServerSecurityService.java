package com.website.online.sale.service.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface ServerSecurityService {
    UserDetails get(String token);
}
