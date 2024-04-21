package com.example.timetrackingapi.controller;

import com.example.timetrackingapi.controller.converter.BreakConverter;
import com.example.timetrackingapi.controller.model.BreakRecord;
import com.example.timetrackingapi.controller.model.PostAttendancesStart200Response;
import com.example.timetrackingapi.controller.model.PostBreaksEndRequest;
import com.example.timetrackingapi.controller.model.PostBreaksStartRequest;
import com.example.timetrackingapi.domain.AttendanceEntity;
import com.example.timetrackingapi.domain.BreakEntity;
import com.example.timetrackingapi.service.AttendanceService;
import com.example.timetrackingapi.service.BreakService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
public class BreakController implements BreaksApi{
    private final AttendanceService attendanceService;
    private final BreakService breakService;
    private final BreakConverter converter;

    @Override
    public ResponseEntity<List<BreakRecord>> getBreaksByUserId(Integer userId, LocalDate date) {
        AttendanceEntity attendanceEntity = attendanceService.getAttendancesByUserIdAndDate(date, userId);
        if (attendanceEntity == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        List<BreakEntity> breakEntityList = breakService.getBreakByAttendance(attendanceEntity.getAttendanceId());
        List<BreakRecord> breakRecordList = breakEntityList
                .stream()
                .map(converter)
                .toList();

        return ResponseEntity.ok(breakRecordList);
    }

    @Override
    public ResponseEntity<PostAttendancesStart200Response> postBreaksEnd(PostBreaksEndRequest request) {
        OffsetDateTime breakEnd = request.getBreakEnd();
        AttendanceEntity attendanceEntity = attendanceService.getAttendancesByUserIdAndDate(
                breakEnd.toLocalDate(),
                request.getUserId());

        var response = new PostAttendancesStart200Response();
        if (attendanceEntity == null) {
            response.setMessage("attendances not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        BreakEntity breakEntity = breakService.postBreakEnd(attendanceEntity, breakEnd);

        if (breakEntity == null) {
            response.setMessage("latest break not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        response.setMessage("break recorded successfully.");
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<PostAttendancesStart200Response> postBreaksStart(PostBreaksStartRequest request) {
        OffsetDateTime breakStart = request.getBreakStart();
        AttendanceEntity attendanceEntity = attendanceService.getAttendancesByUserIdAndDate(
                breakStart.toLocalDate(),
                request.getUserId());

        var response = new PostAttendancesStart200Response();
        if (attendanceEntity == null) {
            response.setMessage("attendances not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        BreakEntity breakEntity = breakService.postBreakStart(attendanceEntity, breakStart);

        if (breakEntity == null) {
            response.setMessage("latest break has not yet ended.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        response.setMessage("break recorded successfully.");
        return ResponseEntity.ok(response);
    }
}
