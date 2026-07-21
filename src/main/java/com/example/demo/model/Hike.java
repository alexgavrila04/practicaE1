package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "hikes")

public class Hike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String start;

    @Column(name = "end_point")
    private String end;

    @ElementCollection
    private List<String> objectives;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private Difficulty dif;

    private String photo;

    public Hike() { // Constructor obligatoriu pentru Hibernate
    }

    public void setObjectives(List<String> objectives) {
        if (objectives != null) {
            this.objectives = new ArrayList<>(objectives);
        } else {
            this.objectives = null;
        }
    }
}