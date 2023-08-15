package com.tenten.hackathon.repository;

import com.tenten.hackathon.entity.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.lang.String;

public interface ExerciseRepository extends JpaRepository<ExerciseEntity, Integer> {
    List<ExerciseEntity> findByUserEmailAndCalendarDate(String userEmail, Date calenderDate);
}
