package com.website.online.sale.dtos.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SanPhamResponse {
    private Long id;
    private String ten;
    private Long gia;
    private Integer soLuong;
    private String moTa;
    private String image;
}
