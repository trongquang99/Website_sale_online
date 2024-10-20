package com.website.online.sale.dtos.request;

import lombok.Data;

import java.util.List;

@Data
public class UserAuthRequest {
    private String username;
    private String password;
    private List<String> scopes;
}
