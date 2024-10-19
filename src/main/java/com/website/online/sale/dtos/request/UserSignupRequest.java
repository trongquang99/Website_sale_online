package com.website.online.sale.dtos.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.Date;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserSignupRequest {
    private String ten;
    private String username;
    private String password;
    private String sdt;
    private String diaChi;
    private Date ngaySinh;
    private String email;

}
