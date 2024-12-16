package com.website.online.sale.dtos.kafka_event;

import lombok.Data;

@Data
public class UserEvent {
    private String username;
    private String password;
}
