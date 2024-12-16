package com.website.online.sale.controller;

import com.website.online.sale.dtos.response.SpecificationProductDto;
import com.website.online.sale.dtos.response.TestA;
import com.website.online.sale.model.ProductSpecification;
import com.website.online.sale.model.User;
import com.website.online.sale.repository.ProductSpecificationRepository;
import com.website.online.sale.repository.UserRepository;
import com.website.online.sale.utils.JsonUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    private final UserRepository userRepository;

    public IndexController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String ok(){
        return "ok";
    }

    @GetMapping("/api/ping")
    public String index(){
        return "ping done";
    }
}
