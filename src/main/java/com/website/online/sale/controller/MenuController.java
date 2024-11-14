package com.website.online.sale.controller;

import com.website.online.sale.dtos.response.MenuResponse;
import com.website.online.sale.service.menu.MenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController
public class MenuController {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/api/v1/menu")
    public List<MenuResponse> menu() {
        List<MenuResponse> menuList = menuService.getAllMenu();
        return menuList;
    }
}
