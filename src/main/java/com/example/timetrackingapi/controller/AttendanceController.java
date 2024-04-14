package com.example.timetrackingapi.controller;

import com.example.timetrackingapi.controller.model.Attendances;
import com.example.timetrackingapi.controller.model.PostAttendancesEndRequest;
import com.example.timetrackingapi.controller.model.PostAttendancesStart200Response;
import com.example.timetrackingapi.controller.model.PostAttendancesStartRequest;
import com.example.timetrackingapi.domain.AttendanceEntity;
import com.example.timetrackingapi.service.AttendanceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@RestController
@AllArgsConstructor
public class AttendanceController implements AttendancesApi {
    private final AttendanceService attendanceService;

    @Override
    public ResponseEntity<Attendances> getAttendanceByUserId(String userId, LocalDate date) {
        return null;
    }

    @Override
    public ResponseEntity<PostAttendancesStart200Response> postAttendancesEnd(PostAttendancesEndRequest postAttendancesEndRequest) {
        return null;
    }

    @Override
    public ResponseEntity<PostAttendancesStart200Response> postAttendancesStart(PostAttendancesStartRequest request) {
        OffsetDateTime timeIn = request.getTimeIn();
        Integer userId = request.getUserId();

        AttendanceEntity attendanceEntity = attendanceService.postAttendancesStart(timeIn, userId);

        var response = new PostAttendancesStart200Response();
        if (attendanceEntity != null) {
            response.setMessage("Attendance recorded successfully.");
            return ResponseEntity.ok(response);
        } else {
            response.setMessage("Attendance is already recorded.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }
}
