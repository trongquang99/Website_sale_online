package com.website.online.sale.controller.product;

import com.website.online.sale.dtos.request.ProductListRequest;
import com.website.online.sale.model.SanPham;
import com.website.online.sale.service.product.ProductListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductListController {
    private final ProductListService productListService;

    public ProductListController(ProductListService productListService) {
        this.productListService = productListService;
    }

//    @PostMapping("/api/product_list")
//    public List<SanPham> productList(
//            @RequestParam(name = "name", required = false) String nameSp,
//            @RequestParam(name = "price", required = false) Long priceSp,
//            @RequestParam(name = "memory", required = false) String memorySp
//    ){
//        List<SanPham> responses = productListService.getProductList(nameSp, priceSp, memorySp);
//        return responses;
//    }
}
