package com.tenten.hackathon.controller;

import com.tenten.hackathon.dto.ExerciseCreateDto;
import com.tenten.hackathon.dto.ResponseDto;
import com.tenten.hackathon.entity.ExerciseEntity;
import com.tenten.hackathon.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    // POST 요청을 처리하는 메서드: 새로운 운동 정보 추가
    @PostMapping("/add")
    public ResponseEntity<ResponseDto<ExerciseEntity>> addExercise(
            @RequestBody ExerciseCreateDto dto,       // 요청 바디에서 ExerciseCreateDto 객체를 받음
            @RequestParam String userEmail            // 요청 파라미터에서 userEmail 값을 받음
    ) {
        ResponseDto<ExerciseEntity> response = exerciseService.addExercise(dto, userEmail);  // Service의 addExercise 메서드 호출
        return ResponseEntity.ok(response);       // 성공한 경우 200 OK 응답과 함께 결과 반환
    }

    // POST 요청을 처리하는 메서드: sets 추가 및 reps, weight 수정
    @PostMapping("/add-sets")
    public ResponseEntity<ResponseDto<ExerciseEntity>> addSetsAndModify(
            @RequestBody ExerciseCreateDto dto,       // 요청 바디에서 ExerciseCreateDto 객체를 받음
            @RequestParam int setsToAdd               // 요청 파라미터에서 setsToAdd 값을 받음
    ) {
        ResponseDto<ExerciseEntity> response = exerciseService.addSetsAndModify(dto, setsToAdd);  // Service의 addSetsAndModify 메서드 호출
        return ResponseEntity.ok(response);       // 성공한 경우 200 OK 응답과 함께 결과 반환
    }

    // POST 요청을 처리하는 메서드: 특정 운동 타입의 sets 삭제
    @PostMapping("/delete-sets")
    public ResponseEntity<ResponseDto<String>> deleteSets(
            @RequestParam int exerciseType,           // 요청 파라미터에서 exerciseType 값을 받음
            @RequestParam int setsToDelete           // 요청 파라미터에서 setsToDelete 값을 받음
    ) {
        ResponseDto<String> response = exerciseService.deleteSets(exerciseType, setsToDelete);  // Service의 deleteSets 메서드 호출
        return ResponseEntity.ok(response);       // 성공한 경우 200 OK 응답과 함께 결과 반환
    }
}
