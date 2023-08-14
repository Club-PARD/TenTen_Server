package com.tenten.hackathon.repository;

import com.tenten.hackathon.entity.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface ExerciseRepository extends JpaRepository<ExerciseEntity, Integer> {
    List<ExerciseEntity> findByCalendarDate(Date calenderDate);
}
