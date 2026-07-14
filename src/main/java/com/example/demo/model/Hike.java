package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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

    public Hike() {
    }

    public Hike(String start, String end, Vector<String> objectives, LocalDate date, Difficulty dif, String photo) {
        this.start = start;
        this.end = end;
        if (objectives != null) {
            this.objectives = new ArrayList<>(objectives);
        }
        this.date = date;
        this.dif = dif;
        this.photo = photo;
    }

    public void setObjectives(List<String> objectives) {
        if (objectives != null) {
            this.objectives = new ArrayList<>(objectives);
        } else {
            this.objectives = null;
        }
    }

}