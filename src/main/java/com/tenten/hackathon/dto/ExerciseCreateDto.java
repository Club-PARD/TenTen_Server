package com.tenten.hackathon.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import java.lang.String;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseCreateDto {

    private int exerciseType;

    private String userEmail;

    private int sets;

    private int reps;

    private double weight;
}
