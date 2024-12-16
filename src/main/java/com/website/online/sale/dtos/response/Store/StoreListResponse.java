package com.website.online.sale.dtos.response.Store;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StoreListResponse {
    private Long id;
    private String name;
    private String address;
    private String phone;
}
