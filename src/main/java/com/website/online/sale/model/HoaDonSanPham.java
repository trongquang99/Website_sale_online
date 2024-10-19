package com.website.online.sale.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "hoadonsanpham")
public class HoaDonSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(name = "id_hd")
    private Long idHd;

    @Column(name = "id_sp")
    private Long idSp;

    @Column(name = "so_luong")
    private Integer soLuong;
}
