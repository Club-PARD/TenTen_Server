package com.tenten.hackathon.controller;

import com.tenten.hackathon.dto.ResponseDto;
import com.tenten.hackathon.entity.ExerciseEntity;
import com.tenten.hackathon.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/calendar")
public class CalendarController {

    private final CalendarService calendarService;

    @Autowired
    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    // GET 요청을 처리하는 메서드: 선택한 날짜의 운동 정보 가져오기
    @GetMapping("/exercises")
    public ResponseEntity<ResponseDto<List<ExerciseEntity>>> getExercisesByDate(
            @RequestParam Date selectedDate  // 요청 파라미터에서 selectedDate 값을 받음
    ) {
        ResponseDto<List<ExerciseEntity>> response = calendarService.getExercisesByDate(selectedDate);  // Service의 getExercisesByDate 메서드 호출
        return ResponseEntity.ok(response);  // 성공한 경우 200 OK 응답과 함께 결과 반환
    }
}
