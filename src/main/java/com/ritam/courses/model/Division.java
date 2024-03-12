package com.ritam.courses.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Division")
@Data
public class Division {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 255)
    private String name;
}
