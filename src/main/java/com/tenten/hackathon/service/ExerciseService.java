package com.tenten.hackathon.service;

import com.tenten.hackathon.dto.ExerciseCreateDto;
import com.tenten.hackathon.dto.ResponseDto;
import com.tenten.hackathon.entity.ExerciseEntity;
import com.tenten.hackathon.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;

@Service
@Transactional
public class ExerciseService {

    @Autowired
    ExerciseRepository exerciseRepository;

    public ResponseDto<List<ExerciseEntity>> getList(String userEmail, LocalDate calendarDate) {
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

//    public ResponseDto<?> updateExercise(Integer id, ExerciseCreateDto dto) {
//        ExerciseEntity exerciseEntity = exerciseRepository.findById(id).get();
//        try {
//            if (dto.getSets() != -1) exerciseEntity.setSets(dto.getSets());
//            if (dto.getReps() != -1) exerciseEntity.setReps(dto.getReps());
//            if (dto.getWeight() != -1) exerciseEntity.setWeight(dto.getWeight());
//
//            return ResponseDto.setSuccess("Exercise update Success", null);
//        } catch (Exception e) {
//            return ResponseDto.setFailed("DB Error");
//        }
//    }

    public ResponseDto<?> updateExercise(Integer id, ExerciseCreateDto dto) {
        try {
            ExerciseEntity exerciseEntity = exerciseRepository.findById(id).get();

            if (exerciseEntity == null) {
                return ResponseDto.setFailed("Exercise not found");
            } else {
                exerciseEntity.setSets(dto.getSets());
                exerciseEntity.setReps(dto.getReps());
                exerciseEntity.setWeight(dto.getWeight());
                exerciseRepository.save(exerciseEntity); // Save the updated entity
            }

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






