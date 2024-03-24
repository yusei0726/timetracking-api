package com.example.timetrackingapi.controller;

import com.example.timetrackingapi.controller.model.Attendances;
import com.example.timetrackingapi.controller.model.PostAttendancesEndRequest;
import com.example.timetrackingapi.controller.model.PostAttendancesStart200Response;
import com.example.timetrackingapi.controller.model.PostAttendancesStartRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class AttendanceController implements AttendancesApi {

    @Override
    public ResponseEntity<Attendances> getAttendanceByUserId(String userId, LocalDate date) {
        return null;
    }

    @Override
    public ResponseEntity<PostAttendancesStart200Response> postAttendancesEnd(PostAttendancesEndRequest postAttendancesEndRequest) {
        return null;
    }

    @Override
    public ResponseEntity<PostAttendancesStart200Response> postAttendancesStart(PostAttendancesStartRequest postAttendancesStartRequest) {
        return null;
    }
}
