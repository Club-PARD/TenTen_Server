package com.tenten.hackathon.service;

import com.tenten.hackathon.dto.ResponseDto;
import com.tenten.hackathon.entity.ExerciseEntity;
import com.tenten.hackathon.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class CalendarService {

    private final ExerciseRepository exerciseRepository;

    @Autowired
    public CalendarService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public ResponseDto<List<ExerciseEntity>> getExercisesByDate(Date selectedDate) {
        try {
            List<ExerciseEntity> exercises = exerciseRepository.findByCalendarDate(selectedDate);

            if (exercises.isEmpty()) {
                return ResponseDto.setFailed("No exercises found for the selected date.");
            }

            return ResponseDto.setSuccess("Exercises for the selected date retrieved successfully.", exercises);
        } catch (Exception e) {
            return ResponseDto.setFailed("An error occurred while fetching exercises: " + e.getMessage());
        }
    }
}
