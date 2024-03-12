package com.ritam.courses.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Course")
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "duration")
    private Long duration;

    @Column(name = "undergraduate")
    private Boolean undergraduate;

    @Column(name = "egLevel")
    private Boolean egLevel;

    @Column(name = "bfCycle")
    private Double bfCycle;

    @ManyToOne
    @JoinColumn(name = "division_id") // Name of the foreign key column in the Course table
    private Division division;
}
