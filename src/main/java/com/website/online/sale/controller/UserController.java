package com.website.online.sale.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.website.online.sale.service.UserService;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("api/v1/user")
    public Object getAllUser (){
        return userService.getAllUser();
    }
}
