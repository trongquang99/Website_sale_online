package com.website.online.sale.dtos.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.Date;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CheckSignupResponse {
    private String ten;
    private String username;
    private String passWord;
    private String sdt;
    private String diaChi;
    private Date ngaySinh;
    private String email;
    private Boolean status;
    private String error;
}
