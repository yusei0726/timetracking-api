package com.example.timetrackingapi.controller;

import com.example.timetrackingapi.controller.model.BreakRecord;
import com.example.timetrackingapi.controller.model.PostAttendancesStart200Response;
import com.example.timetrackingapi.controller.model.PostBreaksEndRequest;
import com.example.timetrackingapi.controller.model.PostBreaksStartRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class BreakController implements BreaksApi{
    @Override
    public ResponseEntity<List<BreakRecord>> getBreaksByUserId(String userId, LocalDate date) {
        return null;
    }

    @Override
    public ResponseEntity<PostAttendancesStart200Response> postBreaksEnd(PostBreaksEndRequest postBreaksEndRequest) {
        return null;
    }

    @Override
    public ResponseEntity<PostAttendancesStart200Response> postBreaksStart(PostBreaksStartRequest postBreaksStartRequest) {
        return null;
    }
}
