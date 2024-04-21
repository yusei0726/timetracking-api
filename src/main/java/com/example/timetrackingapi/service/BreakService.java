package com.example.timetrackingapi.service;

import com.example.timetrackingapi.domain.AttendanceEntity;
import com.example.timetrackingapi.domain.BreakEntity;
import com.example.timetrackingapi.infrastructure.BreakRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class BreakService {
    private final BreakRepository repository;

    public BreakEntity postBreakStart(AttendanceEntity attendanceEntity, OffsetDateTime breakStart) {
        if (attendanceEntity == null || breakStart == null) {
            throw new IllegalArgumentException();
        }

        BreakEntity latestBreak = repository.findLatestBreakByAttendanceId(attendanceEntity.getAttendanceId());
        if (latestBreak != null && latestBreak.getBreakEnd() == null) {
            return null;
        }

        BreakEntity newBreak = new BreakEntity();
        newBreak.setBreakStart(breakStart.toLocalDateTime());
        newBreak.setAttendance(attendanceEntity);
        return repository.save(newBreak);
    }

    public List<BreakEntity> getBreakByAttendance(Integer attendanceId) {
        if (attendanceId == null) {
            throw new IllegalArgumentException();
        }

        return repository.findByAttendanceId(attendanceId);
    }
}
