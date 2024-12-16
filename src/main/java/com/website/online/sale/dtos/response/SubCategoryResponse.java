package com.website.online.sale.dtos.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SubCategoryResponse {
    private Long id;
    private List<BrandProminent> brands;
    private List<SubCategory> subCategories;
    private List<SellingProduct> sellingProducts;
}
