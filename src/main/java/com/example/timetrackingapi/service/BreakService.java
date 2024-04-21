package com.example.timetrackingapi.service;

import com.example.timetrackingapi.domain.AttendanceEntity;
import com.example.timetrackingapi.domain.BreakEntity;
import com.example.timetrackingapi.infrastructure.AttendanceRepository;
import com.example.timetrackingapi.infrastructure.BreakRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class BreakService {
    private final AttendanceRepository attendanceRepository;
    private final BreakRepository breakRepository;


    public List<BreakEntity> getBreakByAttendance(Integer attendanceId) {
        if (attendanceId == null) {
            throw new IllegalArgumentException();
        }

        return breakRepository.findByAttendanceId(attendanceId);
    }
}
