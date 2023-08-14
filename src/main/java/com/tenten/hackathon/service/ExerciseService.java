package com.tenten.hackathon.service;

import com.tenten.hackathon.dto.ExerciseCreateDto;
import com.tenten.hackathon.dto.ResponseDto;
import com.tenten.hackathon.entity.ExerciseEntity;
import com.tenten.hackathon.entity.UserEntity;
import com.tenten.hackathon.repository.ExerciseRepository;
import com.tenten.hackathon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Transactional
    public ResponseDto<ExerciseEntity> addExercise(ExerciseCreateDto dto, String userEmail) {
        try {
            // Find the user by email from the UserRepository
            UserEntity user = userRepository.findByUserEmail(userEmail);
            if (user == null) {
                return ResponseDto.setFailed("addExercise 오류: 등록된 유저가 없음");
            }

            // Create a new ExerciseEntity from the DTO and user
            ExerciseEntity exercise = new ExerciseEntity(dto, user);

            // Save the exercise in the repository
            ExerciseEntity savedExercise = exerciseRepository.save(exercise);

            return ResponseDto.setSuccess("운동 정보 추가 성공", savedExercise);
        } catch (Exception e) {
            return ResponseDto.setFailed("운동 정보 추가 실패: " + e.getMessage());
        }
    }

    @Transactional
    public ResponseDto<ExerciseEntity> addSetsAndModify(ExerciseCreateDto dto, int setsToAdd) {
        try {
            ExerciseEntity exercise = exerciseRepository.findById(dto.getExerciseType()).orElse(null);

            if (exercise == null) {
                return ResponseDto.setFailed("해당 exerciseType의 운동 정보를 찾을 수 없음");
            }

            // Add sets and modify reps and weight
            exercise.setSets(exercise.getSets() + setsToAdd);
            exercise.setReps(dto.getReps());
            exercise.setWeight(dto.getWeight());

            ExerciseEntity updatedExercise = exerciseRepository.save(exercise);

            return ResponseDto.setSuccess("운동 정보 수정 성공", updatedExercise);
        } catch (Exception e) {
            return ResponseDto.setFailed("운동 정보 수정 실패: " + e.getMessage());
        }
    }

    @Transactional
    public ResponseDto<String> deleteSets(int exerciseType, int setsToDelete) {
        try {
            ExerciseEntity exercise = exerciseRepository.findById(exerciseType).orElse(null);

            if (exercise == null) {
                return ResponseDto.setFailed("해당 exerciseType의 운동 정보를 찾을 수 없음");
            }

            // Ensure sets are not negative
            int newSets = Math.max(exercise.getSets() - setsToDelete, 0);
            exercise.setSets(newSets);

            exerciseRepository.save(exercise);

            return ResponseDto.setSuccess("Sets 삭제 성공", "Sets가 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            return ResponseDto.setFailed("Sets 삭제 실패: " + e.getMessage());
        }
    }
}
