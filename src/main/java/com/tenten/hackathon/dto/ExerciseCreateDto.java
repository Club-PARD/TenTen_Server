package com.tenten.hackathon.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import java.lang.String;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseCreateDto {
//    private int exerciseId;
    @NotBlank
    private int exerciseType;
    @NotBlank
    private String userEmail;
//    private Date calendarDate;

    private int sets;

    private int reps;

    private double weight;
}
