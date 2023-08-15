package com.tenten.hackathon.entity;

import com.tenten.hackathon.dto.ExerciseCreateDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import java.lang.String;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "Exercise")
@Table(name = "Exercise")
@Data
public class ExerciseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int exerciseId;

    int exerciseType;

    String userEmail;
    @CreatedDate
    Date calendarDate;
    @ColumnDefault("1")
    int sets;
    @ColumnDefault("0")
    int reps;
    @ColumnDefault("0.0")
    double weight;

    public ExerciseEntity(ExerciseCreateDto dto) {
//        this.exerciseId = dto.getExerciseId();
        this.exerciseType = dto.getExerciseType();
        this.userEmail = dto.getUserEmail();
//        this.calendarDate = dto.getCalendarDate();
        this.sets = dto.getSets();
        this.reps = dto.getReps();
        this.weight = dto.getWeight();
    }

}
