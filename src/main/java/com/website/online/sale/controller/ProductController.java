package com.website.online.sale.controller;

import com.website.online.sale.dtos.response.SanPhamResponse;
import com.website.online.sale.model.SanPham;
import com.website.online.sale.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/timKiemDanhSachSanPham")
    public List<SanPham> sanPhams(
            @RequestParam(name = "text_search") String text
    ){
        try{
            List<SanPham> response = productService.layDanhSachSanPham(text);
            return response;
        }catch (Exception e){
            log.error("get sanpham bi loi", e);
            return null;
        }
    }

    @PostMapping("api/sanPhamChiTiet/{id}")
    public SanPhamResponse sanPhamChiTiet(@PathVariable(name = "id") Long id){
        SanPhamResponse response = productService.laySanPhamChiTiet(id);
        return response;
    }
}
