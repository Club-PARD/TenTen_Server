package com.tenten.hackathon.controller;

import com.tenten.hackathon.dto.ExerciseCreateDto;
import com.tenten.hackathon.dto.ResponseDto;
import com.tenten.hackathon.entity.ExerciseEntity;
import com.tenten.hackathon.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.lang.String;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/exercise")
public class ExerciseController {

    @Autowired
    ExerciseService exerciseService;

    @GetMapping("/list")
    public ResponseDto<List<ExerciseEntity>> getList(
            @RequestParam("userEmail") String userEmail,
            @RequestParam("calendarDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate calendarDate) {

        return exerciseService.getList(userEmail, calendarDate);
    }


    @PostMapping("/create")
    public ResponseDto<?> createExercise(@RequestBody ExerciseCreateDto requestBody) {
        ResponseDto<?> result = exerciseService.createExercise(requestBody);
        return result;
    }

    @PutMapping("/update/{id}")
    public ResponseDto<?> updateExercise(@PathVariable("id") Integer id, @RequestBody ExerciseCreateDto requestBody) {
        return exerciseService.updateExercise(id, requestBody);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto<?> deleteExercise(@PathVariable("id") Integer id) {
        return exerciseService.deleteExercise(id);
    }

}
