package com.website.online.sale.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @GetMapping("/")
    public String ok(){
        return "ok";
    }
    @GetMapping("/api/ping")
    public String index(){
        return "ping done";
    }
}
