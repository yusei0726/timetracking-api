package com.example.timetrackingapi.infrastructure;

import com.example.timetrackingapi.domain.AttendanceEntity;
import com.example.timetrackingapi.domain.BreakEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BreakRepository extends JpaRepository<BreakEntity, Integer> {
    List<BreakEntity> findByAttendance_AttendanceId(@Param("attendanceId") Integer attendanceId);

    Page<BreakEntity> findByAttendance_AttendanceId(@Param("attendanceId") Integer attendanceId, Pageable pageable);
}
