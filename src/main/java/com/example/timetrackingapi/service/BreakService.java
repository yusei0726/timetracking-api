package com.example.timetrackingapi.service;

import com.example.timetrackingapi.domain.AttendanceEntity;
import com.example.timetrackingapi.domain.BreakEntity;
import com.example.timetrackingapi.infrastructure.BreakRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

        PageRequest pageRequest = PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "breakId"));
        Page<BreakEntity> page = repository.findByAttendance_AttendanceId(attendanceEntity.getAttendanceId(), pageRequest);

        if (!page.isEmpty() && page.getContent().get(0).getBreakEnd() == null) {
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

        return repository.findByAttendance_AttendanceId(attendanceId);
    }

    public BreakEntity postBreakEnd(AttendanceEntity attendanceEntity, OffsetDateTime breakEnd) {
        if (attendanceEntity == null || breakEnd == null) {
            throw new IllegalArgumentException();
        }

        PageRequest pageRequest = PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "breakId"));
        Page<BreakEntity> page = repository.findByAttendance_AttendanceId(attendanceEntity.getAttendanceId(), pageRequest);
        if (page.isEmpty()) {
            return null;
        }
        BreakEntity latestBreak = page.getContent().get(0);
        System.out.println(latestBreak.getBreakId());
        latestBreak.setBreakEnd(breakEnd.toLocalDateTime());
        return repository.save(latestBreak);
    }
}
