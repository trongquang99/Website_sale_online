package com.website.online.sale.service.product;

import com.website.online.sale.dtos.response.ProductDetailResponse;
import org.springframework.stereotype.Service;

@Service
public interface ProductDetailService {
    ProductDetailResponse getProductDetail(Long id);
}
