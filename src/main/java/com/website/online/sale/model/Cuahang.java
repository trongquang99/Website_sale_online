package com.website.online.sale.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Data
@Entity
@Table(name = "cuahang")
public class Cuahang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 200)
    @Column(name = "ten", length = 200)
    private String ten;

    @Size(max = 15)
    @Column(name = "sdt", length = 15)
    private String sdt;

    @Size(max = 200)
    @Column(name = "dia_chi", length = 200)
    private String diaChi;

    @Column(name = "created")
    private Instant created;

    @Column(name = "updated")
    private Instant updated;

}