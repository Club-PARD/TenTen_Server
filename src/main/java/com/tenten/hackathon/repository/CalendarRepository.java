package com.tenten.hackathon.repository;

import com.tenten.hackathon.entity.CalendarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.sql.Timestamp;

public interface CalendarRepository extends JpaRepository<CalendarEntity, Timestamp> {
    CalendarEntity findByCalendarDate(Date CalendarDate);
}
