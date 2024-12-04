package com.website.online.sale.dtos.response.menu;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.Date;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class EventResponse {
    private Long id;
    private String title;
    private String description;
    private String image;
    private Date created;
}
