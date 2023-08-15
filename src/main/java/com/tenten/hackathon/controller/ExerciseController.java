package com.tenten.hackathon.controller;

import com.tenten.hackathon.dto.ExerciseCreateDto;
import com.tenten.hackathon.dto.ResponseDto;
import com.tenten.hackathon.entity.ExerciseEntity;
import com.tenten.hackathon.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.lang.String;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/exercise")
public class ExerciseController {

    @Autowired ExerciseService exerciseService;

    @GetMapping("/list")
    public ResponseEntity<ResponseDto<List<ExerciseEntity>>> getList(
            @RequestParam String userEmail,
            @RequestParam Date calendarDate) {
        ResponseDto<List<ExerciseEntity>> response = exerciseService.getList(userEmail, calendarDate);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseDto<?> createExercise(@RequestBody ExerciseCreateDto requestBody) {
        ResponseDto<?> result = exerciseService.createExercise(requestBody);
        return result;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDto<?>> updateExercise(
            @PathVariable Integer id,
            @RequestBody ExerciseCreateDto dto) {
        ResponseDto<?> response = exerciseService.updateExercise(id, dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto<?>> deleteExercise(@PathVariable Integer id) {
        ResponseDto<?> response = exerciseService.deleteExercise(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
