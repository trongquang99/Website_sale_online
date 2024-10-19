package com.website.online.sale.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@Entity
@Table(name = "hoadon")
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(name = "id_kh")
    private Long idKh;

    @Column(name = "id_pttt")
    private Long idPttt;

    @Column(name = "tong_tien")
    private Double tongTien;

    @Column(name = "ngay_thanh_toan")
    private Date ngayThanhToan;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @Column(name = "created")
    private Date created;

    @Column(name = "updated")
    private Date updated;
}
