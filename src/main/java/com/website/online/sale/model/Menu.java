package com.website.online.sale.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "danhmuc")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 100)
    @Column(name = "ten", length = 100)
    private String ten;

    @Size(max = 100)
    @Column(name = "icon", length = 100)
    private String icon;

    @Column(name = "created")
    private Date created;

    @Column(name = "updated")
    private Date updated;

}