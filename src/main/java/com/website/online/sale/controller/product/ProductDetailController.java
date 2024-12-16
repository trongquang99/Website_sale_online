package com.website.online.sale.controller.product;

import com.website.online.sale.dtos.response.ProductDetailResponse;
import com.website.online.sale.service.product.ProductDetailService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductDetailController {
    private final ProductDetailService productDetailService;

    public ProductDetailController(ProductDetailService productDetailService) {
        this.productDetailService = productDetailService;
    }

    @PostMapping("/api/v1/product_detail/{id}")
    public ProductDetailResponse productDetail(@PathVariable(name = "id") Long id){
        ProductDetailResponse response = productDetailService.getProductDetail(id);
        return response;
    }
}
