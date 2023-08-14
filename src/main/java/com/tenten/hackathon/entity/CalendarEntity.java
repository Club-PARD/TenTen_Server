package com.tenten.hackathon.entity;

import com.tenten.hackathon.dto.CalendarCreateDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Calendar")
@Table(name = "Calendar")
@Data
public class CalendarEntity {
    @Id
    @NotBlank
    @OneToOne
    @JoinColumn(name = "calendar_date")
    private ExerciseEntity exercise;
    @NotBlank
    @ManyToOne
    @JoinColumn(name = "user_email")
    private UserEntity user;
    @OneToMany(mappedBy = "exercise_type")
    private List<ExerciseEntity> exercises;

}
