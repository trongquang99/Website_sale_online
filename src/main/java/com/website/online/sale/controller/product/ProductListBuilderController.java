package com.website.online.sale.controller.product;

import com.website.online.sale.dtos.request.ProductListRequest;
import com.website.online.sale.model.SanPham;
import com.website.online.sale.service.product.ProductListBuilderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductListBuilderController {
    private final ProductListBuilderService productListBuilderService;

    public ProductListBuilderController(ProductListBuilderService productListBuilderService) {
        this.productListBuilderService = productListBuilderService;
    }

    @PostMapping("/api/product/query_builder")
    public List<SanPham> productListBuilder(
            @RequestBody ProductListRequest request
    ){
        List<SanPham> responses = productListBuilderService.getProductListBuilder(request);
        return responses;
    }
}
