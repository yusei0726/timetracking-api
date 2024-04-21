package com.example.timetrackingapi.infrastructure;

import com.example.timetrackingapi.domain.AttendanceEntity;
import com.example.timetrackingapi.domain.BreakEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface BreakRepository extends JpaRepository<BreakEntity, Integer> {
    @Query("SELECT b FROM BreakEntity b WHERE b.attendance.attendanceId = :attendanceId ORDER BY b.breakEnd DESC")
    List<BreakEntity> findByAttendanceId(@Param("attendanceId") Integer attendanceId);

    @Query("SELECT b FROM BreakEntity b WHERE b.attendance.attendanceId = :attendanceId ORDER BY b.breakEnd DESC")
    BreakEntity findLatestBreakByAttendanceId(@Param("attendanceId") Integer attendanceId);
}
