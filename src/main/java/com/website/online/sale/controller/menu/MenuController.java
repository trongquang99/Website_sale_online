package com.website.online.sale.controller.menu;

import com.website.online.sale.dtos.request.menu.WarrantyRequest;
import com.website.online.sale.dtos.response.MenuResponse;
import com.website.online.sale.dtos.response.SubCategoryResponse;
import com.website.online.sale.dtos.response.menu.EventResponse;
import com.website.online.sale.dtos.response.menu.WarrantyResponse;
import com.website.online.sale.service.menu.MenuService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/api/v1/subcategory_menu/{id}")
    public SubCategoryResponse getSubCategoryMenu(@PathVariable(name = "id") Long id) {
        SubCategoryResponse response = menuService.getSubCategoryMenu(id);
        return response;
    }

    @GetMapping("/api/v1/warranty_check")
    public List<WarrantyResponse> getWarrantyCheck(
            @ParameterObject WarrantyRequest warrantyRequest
    ) {
        List<WarrantyResponse> response = menuService.getWarrantyCheck(warrantyRequest);
         return response;
    }

    @GetMapping("/api/v1/event")
    public List<EventResponse> getEvent(){
        List<EventResponse> response = menuService.getAllEvent();
        return response;
    }
}
