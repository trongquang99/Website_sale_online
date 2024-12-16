package com.website.online.sale.dtos.response.product;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.website.online.sale.dtos.response.SpecificationProductDto;
import com.website.online.sale.model.SanPham;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ListProductResponse {
    private Long id;
    private String name;
    private Integer quantity;
    private Long price;
    private String image;
    private Double discount;
    private SpecificationProductDto specification;
}
