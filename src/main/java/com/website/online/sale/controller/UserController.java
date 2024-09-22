package com.website.online.sale.controller;

import com.website.online.sale.dtos.request.UserLoginRequest;
import com.website.online.sale.dtos.response.CheckLoginResponse;
import org.springframework.web.bind.annotation.*;
import com.website.online.sale.service.UserService;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/v1/user")
    public Object getAllUser (){
        return userService.getAllUser();
    }

    @PostMapping("/api/v1/check_login")
    public CheckLoginResponse checkLogin (
            @RequestBody UserLoginRequest request
    ){
        return userService.checkLogin(request.getUsername(), request.getPassword());
    }
}
