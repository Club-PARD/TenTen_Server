package com.tenten.hackathon.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseCreateDto {

    private int exerciseType;

    private Date calendarDate;
    private int sets;
    private int reps;
    private double weight;
}
