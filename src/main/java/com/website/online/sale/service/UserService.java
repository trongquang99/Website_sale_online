package com.website.online.sale.service;

import com.website.online.sale.dtos.response.CheckLoginResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    Object getAllUser();

    CheckLoginResponse checkLogin(String username, String pass);
}
