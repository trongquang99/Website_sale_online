package com.website.online.sale.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Table(name = "user")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String userName;

    @Column(name = "pass")
    private String pass;

    @Column(name = "created")
    private Date created;

    @Column(name = "updated")
    private Date updated;
}
