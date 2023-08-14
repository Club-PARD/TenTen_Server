package com.tenten.hackathon.entity;
import com.tenten.hackathon.dto.ExerciseCreateDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import java.sql.Date;


@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Exercise")
@Table(name = "Exercise")
@Data
public class ExerciseEntity {
    @Id
    @NotBlank
    int exerciseType;
    @NotBlank
    @ManyToOne
    @JoinColumn(name = "user_email")
    private UserEntity user;
    @NotBlank
    @ColumnDefault("DATE DEFAULT CURRENT_DATE")
    Date calendarDate;
    @ColumnDefault("1")
    int sets;
    @ColumnDefault("1")
    int reps;
    @ColumnDefault("0.0")
    double weight;

    public ExerciseEntity(ExerciseCreateDto dto, UserEntity user) {
        this.exerciseType = dto.getExerciseType();
        this.user = user;
        this.calendarDate = dto.getCalendarDate();
        this.sets = dto.getSets();
        this.reps = dto.getReps();
        this.weight = dto.getWeight();
    }

}
