package com.website.online.sale.service.shopping_cart;

import com.website.online.sale.dtos.response.ShoppingCartResponse;
import org.springframework.stereotype.Service;

@Service
public interface ShoppingCartService {
    ShoppingCartResponse getCartDetail(Long id);
}
