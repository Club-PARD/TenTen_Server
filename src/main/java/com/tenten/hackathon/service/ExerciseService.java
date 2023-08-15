package com.tenten.hackathon.service;

import com.tenten.hackathon.dto.ExerciseCreateDto;
import com.tenten.hackathon.dto.ResponseDto;
import com.tenten.hackathon.entity.ExerciseEntity;
import com.tenten.hackathon.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.lang.String;

@Service
@Transactional
public class ExerciseService {

    @Autowired
    ExerciseRepository exerciseRepository;

    public ResponseDto<List<ExerciseEntity>> getList(String userEmail, Date calendarDate) {
        List<ExerciseEntity> exerciseList = new ArrayList<ExerciseEntity>();

        try {
            exerciseList = exerciseRepository.findByUserEmailAndCalendarDate(userEmail, calendarDate);
            return ResponseDto.setSuccess("Success", exerciseList);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("DB Error");
        }
    }

    public ResponseDto<ExerciseEntity> createExercise(ExerciseCreateDto dto) {
        ExerciseEntity exerciseEntity = new ExerciseEntity(dto);
        try {
            exerciseRepository.save(exerciseEntity);
        } catch (Exception e) {
            return ResponseDto.setFailed("Database Error!");
        }

        return ResponseDto.setSuccess("Exercise Create Success", null);
    }

    public ResponseDto<?> updateExercise(int id, ExerciseCreateDto dto) {
        ExerciseEntity exerciseEntity = exerciseRepository.findById(id).get();
        try {
            exerciseEntity.setReps(dto.getReps());
            exerciseEntity.setWeight(dto.getWeight());

            return ResponseDto.setSuccess("Exercise update Success", null);
        } catch (Exception e) {
            return ResponseDto.setFailed("DB Error");
        }
    }

    public ResponseDto<?> deleteExercise(Integer id) {
        try {
            if (!exerciseRepository.existsById(id)) {
                return ResponseDto.setFailed("No exists");
            }
            exerciseRepository.deleteById(id);
            return ResponseDto.setSuccess("Exercise Delete Success", null);
        } catch (Exception e) {
            return ResponseDto.setFailed("DB Error");
        }

    }
}






