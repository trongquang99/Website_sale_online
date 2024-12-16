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
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 200)
    @Column(name = "title", length = 200)
    private String title;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @Size(max = 200)
    @Column(name = "image", length = 200)
    private String image;

    @Column(name = "created")
    private Instant created;

    @Column(name = "updated")
    private Instant updated;

}