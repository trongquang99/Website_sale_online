package com.website.online.sale.handlers;


import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TimeOutProcess extends RuntimeException {
    private static final String BASE_MESSAGE = "timeout process : ";
    public TimeOutProcess(String message) {
        super(BASE_MESSAGE + message);
    }
}
