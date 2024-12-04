package com.website.online.sale.controller.shopping_cart;

import com.website.online.sale.base.ResponseBuilder;
import com.website.online.sale.dtos.response.ShoppingCartResponse;
import com.website.online.sale.handlers.CustomBadRequestException;
import com.website.online.sale.service.shopping_cart.ShoppingCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/api/v1/shopping_cart/{id}")
    public ResponseEntity<?> cartDetail(
            @PathVariable(name = "id") Long id
    ) {
        ShoppingCartResponse response = shoppingCartService.getCartDetail(id);
        return ResponseEntity.ok(ResponseBuilder.success(response));
    }
}
