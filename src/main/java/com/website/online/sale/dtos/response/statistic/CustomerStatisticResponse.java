package com.website.online.sale.dtos.response.statistic;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CustomerStatisticResponse {
    private Long id;
    private String name;
    private String phone;
    private String address;
    private List<PurchasedProduct> purchasedProducts;
    private List<PurchasedProductType> purchasedProductTypes;
    private Long numberPurchasedProduct;
    private Integer numberPurchasedProductType;

    @Data
    public static class PurchasedProduct {
        private Long id;
        private String name;
        private Integer quantity;
    }
}
