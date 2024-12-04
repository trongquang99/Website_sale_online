package com.website.online.sale.dtos.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductDetailResponse {
    private Long id;
    private String name;
    private Integer quantity;
    private Long price;
    private String image;
    private String description;
    private String promotion;
    private Double discount;
    private SpecificationProductDto specifications;

}
