package com.website.online.sale.dtos.response.statistic;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductStatisticResponse {
    private Long id;
    private String name;
    private Long price;
    private Double discount;
    private Double totalRevenue;
    private Double totalProfit;
}
