package com.example.demo.dto;

import com.example.demo.model.Difficulty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class HikeDTO {
    private String start;

    private String end;

    private List<String> objectives;

    private LocalDate date;

    private Difficulty dif;

    private String photo;


}
