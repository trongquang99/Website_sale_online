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
@Table(name = "danhmuccon")
public class Danhmuccon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "id_dm")
    private Long idDm;

    @Size(max = 100)
    @Column(name = "ten", length = 100)
    private String ten;

    @Size(max = 100)
    @Column(name = "icon", length = 100)
    private String icon;

    @Column(name = "created")
    private Instant created;

    @Column(name = "updated")
    private Instant updated;

}