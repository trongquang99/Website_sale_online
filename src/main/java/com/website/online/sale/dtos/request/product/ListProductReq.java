package com.website.online.sale.dtos.request.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ListProductReq {
    private String name;
    private Long price;
    private String storageRom;
    private Long priceFrom;
    private Long priceTo;
    private String batteryCapacity;
    private Date dateFrom;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateTo;
}
