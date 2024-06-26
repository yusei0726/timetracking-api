package com.example.timetrackingapi.service;

import com.example.timetrackingapi.domain.AttendanceEntity;
import com.example.timetrackingapi.infrastructure.AttendanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AttendanceService {
    private final AttendanceRepository repository;

    public AttendanceEntity postAttendancesStart(OffsetDateTime timeIn, String userId) {
        if (timeIn == null || userId == null) {
            throw new IllegalArgumentException();
        }

        List<AttendanceEntity> existingAttendance = repository.findByUserIdAndDate(userId, timeIn.toLocalDate());
        if (existingAttendance.isEmpty()) {
            AttendanceEntity newAttendance = new AttendanceEntity();
            newAttendance.setUserId(userId);
            newAttendance.setDate(timeIn.toLocalDate());
            newAttendance.setTimeIn(timeIn.toLocalDateTime());
            return repository.save(newAttendance);
        } else {
            return null;
        }
    }

    public AttendanceEntity postAttendancesEnd(OffsetDateTime timeOut, String userId) {
        if (timeOut == null || userId == null) {
            throw new IllegalArgumentException();
        }
        List<AttendanceEntity> existingAttendances = repository.findByUserIdAndDate(userId, timeOut.toLocalDate());
        if (existingAttendances.isEmpty()) {
            return null;
        }

        AttendanceEntity attendance = existingAttendances.get(0);
        attendance.setTimeOut(timeOut.toLocalDateTime());
        return repository.save(attendance);
    }

    public AttendanceEntity getAttendancesByUserIdAndDate(LocalDate date, String userId) {
        if (date == null || userId == null) {
            throw new IllegalArgumentException();
        }
        List<AttendanceEntity> attendance = repository.findByUserIdAndDate(userId, date);
        return attendance.isEmpty() ? null : attendance.get(0);
    }
}
