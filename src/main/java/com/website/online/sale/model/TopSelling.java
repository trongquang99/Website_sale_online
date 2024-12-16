package com.website.online.sale.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Data
@Entity
@Table(name = "top_selling")
public class TopSelling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "id_category")
    private Long idCategory;

    @Column(name = "id_product")
    private Long idProduct;

    @Column(name = "created")
    private Instant created;

    @Column(name = "updated")
    private Instant updated;

}