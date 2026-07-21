package com.example.demo.dto;

import com.example.demo.model.Difficulty;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class HikeDTO {

    @NotBlank(message = "Punctul de plecare nu poate fi gol!")
    @Size(min = 3, max = 50, message = "Punctul de plecare trebuie să aibă între 3 și 50 de caractere")
    private String start;

    @NotBlank(message = "Punctul de sosire nu poate fi gol!")
    private String end;

    @NotNull(message = "Trebuie să adaugi cel puțin un obiectiv!")
    @Size(min = 1, message = "Lista de obiective nu poate fi goală")
    private List<String> objectives;

    @NotNull(message = "Data este obligatorie!")
    @FutureOrPresent(message = "Data drumeției nu poate fi în trecut!")
    private LocalDate date;

    @NotNull(message = "Dificultatea trebuie specificată!")
    private Difficulty dif;

    private String photo;


}
