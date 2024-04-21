package com.example.timetrackingapi.infrastructure;

import com.example.timetrackingapi.domain.AttendanceEntity;
import com.example.timetrackingapi.domain.BreakEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BreakRepository extends JpaRepository<AttendanceEntity, Integer> {
    List<BreakEntity> findByAttendanceId(Integer attendanceId);
}
