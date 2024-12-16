package com.website.online.sale.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "sanpham")
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(name = "id_ch")
    private Long idCh;

    @Column(name = "id_dmc")
    private Long idDmc;

    @Column(name = "ten")
    private String ten;

    @Column(name = "gia")
    private Long gia;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "kieu")
    private Integer kieu;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "image")
    private String image;

    @Column(name = "purchase_price")
    private Long purchasePrice;

    @Column(name = "sale_count")
    private Integer saleCount;

    @Column(name = "created")
    private Date created;

    @Column(name = "updated")
    private Date updated;
}
