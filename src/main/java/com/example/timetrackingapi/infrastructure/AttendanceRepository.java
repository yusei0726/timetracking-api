package com.example.timetrackingapi.infrastructure;

import com.example.timetrackingapi.domain.AttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Integer> {
    List<AttendanceEntity> findByUserIdAndDate(String userId, LocalDate date);
}
