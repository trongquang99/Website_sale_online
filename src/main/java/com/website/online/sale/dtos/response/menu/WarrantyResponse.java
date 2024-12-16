package com.website.online.sale.dtos.response.menu;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class WarrantyResponse {
    private Long id;
    private String name;
    private Integer quantity;
    private Long price;
    private String image;
    private String description;
}
